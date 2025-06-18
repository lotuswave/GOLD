package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HY_ST_108_Validation_Of_Homepage {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Dataset");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Homepage_Elements_Displayed  () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.Store_Logo_Validation();
			Hydro.Hero_Banner_Validation();
			Hydro.CatogeryORproduct_Tile_Validation();
			Hydro.CatogeryORproduct_Slider_Validation();
			Hydro.Promo_Block_Validation();
			Hydro.Marketing_Flyout_Validation();
			

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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
