package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_167_Registeruser_checkout_Simple_bundle_products_USPS_Ground_express_paypal {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registeruser_checkout_Simple_bundle_products_USPS_Ground_express_paypal  () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.search_product("Bundle Product");  
			Drybar.Addtocart_Bundle("Bundle Product"); 
			Drybar.minicart_Checkout();
			Drybar.Express_Paypal("PaypalDetails");  

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
