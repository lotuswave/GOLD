package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_StoreLocator_025_Alternate_Path_User_Selects_Retailers_Tab {

	
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_StoreLocator_AlternatePath_User_Selects_Retailer_Tab() throws Exception {

		try {
			Hydro.ClosADD();
			Hydro.validate_Homepage();
			Hydro.Click_Findstore();
			Hydro.Validate_store_sidebar();
			Hydro.Validate_Retailers();
		
		
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




