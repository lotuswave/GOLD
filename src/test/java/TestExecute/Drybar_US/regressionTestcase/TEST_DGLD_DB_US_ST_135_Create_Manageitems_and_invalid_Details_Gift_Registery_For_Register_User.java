package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_135_Create_Manageitems_and_invalid_Details_Gift_Registery_For_Register_User {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"GiftRegistry");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_Manageitems_and_invalid_Details_Gift_Registery_For_Register_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("Giftaccount");
			Drybar.giftCreation("Birthday");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
			Drybar.additems_giftregistry("Product Qunatity");
			Drybar.noitems_giftregistry("Baby Registry");
			Drybar.share_invalid_details("Baby Registry");
			
			

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
