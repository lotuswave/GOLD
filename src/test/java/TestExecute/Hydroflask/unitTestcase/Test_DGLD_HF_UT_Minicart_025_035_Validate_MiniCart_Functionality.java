package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Minicart_025_035_Validate_MiniCart_Functionality {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_MiniCart_Functionality() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.minicart();
			Hydro.search_product("crosssell");
			Hydro.minicart_crosssell("crosssell");
//			Hydro.shop_bottle("Bottles & Drinkware");
//			Hydro.headerlinks("QA_Testing");
			Hydro.search_product("Product");
			Hydro.minicart_freeshipping("Product");
			Hydro.minicart_delete("delete product");
			Hydro.minicart_update("Quantity");
			Hydro.minicart_click_productname();
			Hydro.minicart_product_close();
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();

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
