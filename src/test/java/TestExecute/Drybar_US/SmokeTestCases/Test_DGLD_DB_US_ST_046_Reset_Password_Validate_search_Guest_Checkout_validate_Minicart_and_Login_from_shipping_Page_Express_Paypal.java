package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarus_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_US_ST_046_Reset_Password_Validate_search_Guest_Checkout_validate_Minicart_and_Login_from_shipping_Page_Express_Paypal {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarus_PRODHyvaHelper Drybar = new GoldDrybarus_PRODHyvaHelper(datafile,"DataSet");
	GoldDrybarus_PRODHyvaHelper Drybar1 = new GoldDrybarus_PRODHyvaHelper(datafile,"My AccountPage");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Reset_Password_Validate_search_Guest_Checkout_validate_Minicart_and_Login_from_shipping_Page_Express_Paypal () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.Forgot_password("AccountDetails");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.remove_Free_Product_website();
			Drybar.clickontheproduct_and_image("Product");
			Drybar.minicart_delete("Product");                    
			Drybar.minicart_validation("Product");	
			Drybar.minicart_Checkout();
			Drybar.addDeliveryAddress_Guestuser("Address");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.Express_Paypal("PaypalDetails");  
			Drybar.paypal_close();			
			Drybar.click_singin_Shippingpage();
			Drybar.login_Drybar("AccountDetails");
			

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}