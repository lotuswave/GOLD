package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_OXO_UT_Footer_005_VerifingContactUSErrorMessage {

	String datafile = "OXO//OxoTestData.xlsx";
	OxoHelper OXO=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void contactUserrormessage() throws Exception {

		try {
			OXO.verifingHomePage();
			OXO.verifingContactUSErrorMessage();
		
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
