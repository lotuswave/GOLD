package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Minicart_041_Additional_Path_Verify_scroll_on_mini_cart {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Additional_Path_Verify_scroll_on_mini_cart() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.shop_bottle("Bottles & Drinkware");
			Hydro.bottles_addtocart_pdp("Product");
//			Hydro.headerlinks("Accessories");   //use in stage
//			Hydro.minicart_freeshipping("Product");   //use in stage
//			Hydro.shop_bottle("Bottles & Drinkware");   //use in stage
//			Hydro.shop_addtocart("Shopproduct");       //use in stage
			Hydro.shop_QAtest("QA_Testing");
			Hydro.QAtest_addtocart_pdp("qa testing");
			Hydro.search_product_pdp("search");
			Hydro.minicart_scroll();
			
			

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
