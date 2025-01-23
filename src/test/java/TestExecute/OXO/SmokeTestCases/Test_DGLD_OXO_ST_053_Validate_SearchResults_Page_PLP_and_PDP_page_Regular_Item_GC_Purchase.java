package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_053_Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase {


	String datafile = "OXO//GoldOxoTestData.xlsx";
	
	GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile, "Search");
	GoldOxoHyva_PRODHelper Oxo1=new GoldOxoHyva_PRODHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.Invalid_search_product("Invalid_Search");
			Oxo.search_product("Valid_Search");
			Oxo.Sort_By("SortBy");
			Oxo.Filter();

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
		System.setProperty("configFile", "oxo\\config.properties");
		Login.signIn();
		Oxo.acceptPrivacy();
	}

}
