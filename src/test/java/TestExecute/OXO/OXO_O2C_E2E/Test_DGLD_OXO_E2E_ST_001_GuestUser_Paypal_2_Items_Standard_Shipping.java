package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_ST_001_GuestUser_Paypal_2_Items_Standard_Shipping {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Paypal_2_Items_Standard_Shipping() throws Exception {
//       for(int i=0;i<3;i++)
//       {
		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			Thread.sleep(5000);
			//String Website=Oxo.URL();
			String Description ="GuestUser_Paypal_2_Items_Standard_Shipping";
			Oxo.verifingHomePage();
			Oxo.search_product("SKU-11244200 - 2QTY");
			Oxo.addtocart("SKU-11244200 - 2QTY");
			Oxo.search_product("SKU-12171000 - 2QTY");
			Oxo.addtocart("SKU-12171000 - 2QTY");
			Oxo.search_product("SKU-9109200 - 2QTY");
			Oxo.addtocart("SKU-9109200 - 2QTY");
			Oxo.minicart_viewcart();
			Oxo.Gift_Message("GiftMessage");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_Guest("AccountDetails");
			String Used_GiftCode = "NULL";
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			String OrderNumber=Oxo.payPal_Payment("PaypalDetails");
			Oxo.Admin_signin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);

			
			
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
//	}
	
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
