package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;


public class Test_DGLD_HF_UT_MyOrderListing_063_NormalPath_UserView_MyOrders_Pagewith_NO_Orders {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void UserView_MyOrders_Pagewith_NO_Orders() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.validateCreateAccountpageNavigation();
			Hydro.validateaccountcreationpassword("AccountDetails");
			Hydro.My_Orders_Page();
			Hydro.myOrders_Shopcategory();

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	 @BeforeTest
	
	    public void startTest() throws Exception {
			System.setProperty("configFile", "Hydroflask\\config.properties");
   Login.signIn();
   Hydro.ClosADD();
	         

	      }


}
