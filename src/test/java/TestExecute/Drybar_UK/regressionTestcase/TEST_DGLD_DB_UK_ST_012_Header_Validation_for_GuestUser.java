package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_012_Header_Validation_for_GuestUser {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void TEST_DGLD_DB_US_ST_012_Header_Validation_for_GuestUser () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
