package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_031_Footer_Links_Validation {

	String datafile = "OXO//GoldOxoTestData.xlsx";
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile, "Footer");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Footer_Links() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.WeAre_Oxo("WeAreOXO");
			Oxo.CustomerSupport("CustomerSupport");
			Oxo.Product_Support("ProductSupport");
			Oxo.Newtab_Footerlinks("Newtab_Footer");

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
