package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_009_Verify_Forgot_Password_page_validation {
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_user_checkout_with_Expedited_shipping_method () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.Forgot_password("AccountDetails");
		/*	Drybar.search_product("Configurable Product");
			Drybar.addtocart("Configurable Product");
			Drybar.HairTools_headerlinks("Hair Tools");
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.click_singin_Shippingpage();
			Drybar.Forgot_password("AccountDetails");*/
			

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
