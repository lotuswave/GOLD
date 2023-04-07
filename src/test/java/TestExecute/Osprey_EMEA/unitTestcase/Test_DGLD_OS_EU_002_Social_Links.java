package TestExecute.Osprey_EMEA.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.GoldOspreyEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_002_Social_Links {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	GoldOspreyEMEAHelper Osprey_Eu = new GoldOspreyEMEAHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Social_Links_Functionality () throws Exception {

		try {
        Osprey_Eu.verifingHomePage();
        Osprey_Eu.social_Links("SocialLinks");
			
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
