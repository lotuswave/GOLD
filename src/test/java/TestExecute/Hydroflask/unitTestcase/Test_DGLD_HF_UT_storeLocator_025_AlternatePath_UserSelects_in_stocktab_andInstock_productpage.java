package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_storeLocator_025_AlternatePath_UserSelects_in_stocktab_andInstock_productpage {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_UserSelects_in_Stocktab_and_instock_productpage() throws Exception {

		try {
			Hydro.ClosADD();
			Hydro.validate_Homepage();
			Hydro.Click_Findstore();			
			Hydro.Click_Instock();
            Hydro.Click_filter();
            Hydro.selectproduct_filter();
            Hydro.click_Retailer1();
            
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

	}

}
