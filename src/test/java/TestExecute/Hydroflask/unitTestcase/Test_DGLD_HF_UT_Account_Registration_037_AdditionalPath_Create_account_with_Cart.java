package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Account_Registration_037_AdditionalPath_Create_account_with_Cart {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void AdditionalPath_Create_account_with_Cart() throws Exception {

		try {
			
			Hydro.verifingHomePage();
		//	Hydro.headerlinks("QA_Testing");  
			Hydro.search_product("Product");
			Hydro.addtocart("Product");  
//			Hydro.headerlinks("Bottles & Drinkware"); 
//			Hydro.bottles_addtocart_pdp("Product");
			String minicart=Hydro.minicart_items();
			Hydro.validateCreateAccountpageNavigation();
			Hydro.validateaccountcreationPassword("AccountDetails");
			Hydro.minicart_products(minicart);
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
