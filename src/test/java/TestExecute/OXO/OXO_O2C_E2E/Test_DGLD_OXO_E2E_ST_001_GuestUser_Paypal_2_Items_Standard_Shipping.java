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
			Oxo.search_product("SKU-21081");
			Oxo.addtocart("SKU-21081");
			Oxo.search_product("SKU-1130900");
			Oxo.addtocart("SKU-1130900");
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
