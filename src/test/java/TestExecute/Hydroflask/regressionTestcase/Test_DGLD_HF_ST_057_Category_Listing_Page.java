package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_057_Category_Listing_Page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"Sheet1");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_Category_Listing_Page () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.CLP_Page("Bottles & Drinkware");
			Hydro.CLP_Page("Kitchenware");
			Hydro.CLP_Page("Accessories");
			Hydro.CLP_Page("Featured");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
