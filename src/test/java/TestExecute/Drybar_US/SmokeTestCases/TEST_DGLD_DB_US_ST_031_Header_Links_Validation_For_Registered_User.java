package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestComponent.Drybar_US.GoldDrybarus_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_031_Header_Links_Validation_For_Registered_User {
	
	

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarus_PRODHyvaHelper Drybar = new GoldDrybarus_PRODHyvaHelper(datafile,"Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Header_Links_Validation_For_Registered_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.header_Hairproducts("Hair Products");
			Drybar.Hairproducts_Shop_by_size("Shop by Size");
			Drybar.Benefits_Ingredients("Ingredients");		
			Drybar.header_HairTools("Hair Tools");
			Drybar.header_Brushes("HTBrushes");
			Drybar.header_Benfits("HeaderBenfits");
			Drybar.header_GiftsSets("Gifts and Sets");
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