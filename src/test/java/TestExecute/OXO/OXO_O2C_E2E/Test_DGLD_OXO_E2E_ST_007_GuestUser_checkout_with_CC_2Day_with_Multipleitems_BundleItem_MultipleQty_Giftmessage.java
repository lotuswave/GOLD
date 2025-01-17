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
			String Description ="Guest user checkout with CreditCard-Visa  (2 Day) - Multiple items + Bundle Item + Multiple Qty +  Gift message(greater than 50 charecters)";
			Oxo.verifingHomePage();
			Oxo.search_product("SKU-11261400 - 2QTY");
			Oxo.addtocart("SKU-11261400 - 2QTY");
			Oxo.search_product("SKU-32480 -2QTY");
			Oxo.addtocart("SKU-32480 -2QTY");
			Oxo.search_product("Bundle Product");
			Oxo.addtocart("Bundle Product");
			Oxo.minicart_viewcart();
			Oxo.Gift_Message("Giftmessage50letters");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_Guest("AccountDetails");
		    Oxo.select_Shipping_Method("2 Day method");
			String Used_GiftCode="NULL";
			String OrderNumber=Oxo.updatePaymentAndSubmitOrder("PaymentDetails"); 
			Oxo.Admin_signin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode);
	

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
