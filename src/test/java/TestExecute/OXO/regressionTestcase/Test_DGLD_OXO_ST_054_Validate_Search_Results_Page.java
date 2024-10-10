package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_054_Validate_Search_Results_Page {

	String datafile = "OXO//GoldOxoTestData.xlsx";
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile, "Search");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Search_Results() throws Exception {

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
