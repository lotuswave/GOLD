package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_042_Validate_Store_Locator {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Store_Locator_Functionality () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.Click_Findstore();
			Hydro.click_Retailer();
			Hydro.verifingRetailerHours();
			Hydro.verifingRetailerBrowser();
			Hydro.Validate_store_sidebar();
			Hydro.CLick_Usemylocation();
			Hydro.Validate_AvailableRetailers();
			Hydro.Validate_retailerlocations();
			Hydro.Click_Instock();
			Hydro.selectproduct("Small Bottle Boot");
			

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
