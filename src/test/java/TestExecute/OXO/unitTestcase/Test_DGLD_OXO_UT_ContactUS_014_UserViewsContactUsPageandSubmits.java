package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_ContactUS_014_UserViewsContactUsPageandSubmits {
	String datafile = "OXO//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Validate_contactus_page_submits() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.UserViewsContactUsPageandSubmits("AccountDetails");
		
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
		System.setProperty("configFile", "OXO\\config.properties");
		Login.signIn();

	}

}
