package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_StoreLocator_023_AlternatePath_User_Selects_Retailer_CTAs {

	
	
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_StoreLocator_AlternatePath_User_Selects_Retailer_CTAs() throws Exception {

		try {
			Hydro.validate_Homepage();
			Hydro.Click_Storelocator();
			Hydro.Validate_store_sidebar();			
			Hydro.click_Retailer();
			Hydro.Click_Browserstock();
			Hydro.Click_Direction();			
	        Hydro.writeReviews();
			

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
		 Hydro.ClosADD();

	}

}





