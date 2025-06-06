package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_031_Header_Links_Validation_For_Registered_User {
	
	

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Header_Links_Validation_For_Registered_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.header_Hairproducts("Hair Products");
			Drybar.header_HairTools("Hair Tools");
			Drybar.header_Brushes("HTBrushes");
			Drybar.header_Benfits("HeaderBenfits");
			Drybar.header_GiftsSets("Gifts and Sets");
			Drybar.header_New("HeaderNew");
			Drybar.header_HowToInspo("How To Inspo");
			
			

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
