package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_EMEA_RT_032_Validate_StoreLocator {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
