package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_099_Validate_Footer_links {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile,"Footer Links");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Footer_Links_Funtionality () throws Exception {

		try {
			 Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.Kustomer_Links("Kustomer");
		        Osprey_ReEu.Footer_validation("Breadcrumbs");
		        Osprey_ReEu.Footer_Links("Footer");
		        Osprey_ReEu.Footer_Links_Resources("Footer");
		        Osprey_ReEu.Footer_Links_BrandTeam("Footer");
		    //  Osprey_ReEu.Footer_Links_Repari_And_Replacement("Footer");            //recaptcha issue
        
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();

	}

}
