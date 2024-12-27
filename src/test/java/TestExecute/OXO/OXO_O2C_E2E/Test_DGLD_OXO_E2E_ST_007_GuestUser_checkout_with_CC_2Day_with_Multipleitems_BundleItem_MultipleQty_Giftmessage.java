package TestExecute.OXO.OXO_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.*;

public class Test_DGLD_OXO_E2E_ST_007_GuestUser_checkout_with_CC_2Day_with_Multipleitems_BundleItem_MultipleQty_Giftmessage {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_checkout_with_CC_2Day_with_Multipleitems_BundleItem_MultipleQty_Giftmessage () throws Exception {
		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			String Description ="GuestUser_Afterpay_2_Items_Standard_Shipping ";
			Oxo.verifingHomePage();
			Oxo.search_E2E_product("SKU-11262700");
			Oxo.Addtocart("SKU-11262700");
			Oxo.search_E2E_product("SKU-11404000");
			Oxo.Addtocart("SKU-11404000");
		    Oxo.minicart_Checkout();
			String Products_details=Oxo.shipping_order_details();
			HashMap<String,String> Shipping=Oxo.Shipingdetails("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			HashMap<String,String> data=Oxo.OrderSummaryValidation();
			String Used_GiftCode="Null";
			String OrderNumber=Oxo.updatePaymentAndSubmitOrder("PaymentDetails"); 
			String OrderIdNumber= Oxo.Verify_order_page();
			System.out.println(OrderIdNumber);
			Oxo.Admin_signin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);
	

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	
	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();

	}

}
