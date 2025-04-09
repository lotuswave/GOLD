package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.It_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_COMMON_099_Validate_Footer_links {


	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Footer Links");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Footer_Links_Funtionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Kustomer_Links("It Kustomer");                  //Kustomer,Sw Kustomer,es Kustomer,It Kustomer,fr Kustomer
        Osprey_ReEu.Footer_validation("It Breadcrumbs");            //Breadcrumbs,Sw Breadcrumbs,es Breadcrumbs,It Breadcrumbs,fr Breadcrumbs
        Osprey_ReEu.Footer_Links("It Footer");        				//Footer,Sw Footer,es Footer,It Footer,fr Footer
        Osprey_ReEu.Footer_Links_Resources("It Footer");			//Footer,Sw Footer,es Footer,It Footer,fr Footer
        Osprey_ReEu.Footer_Links_BrandTeam("It Footer");  			//Footer,Sw Footer,es Footer,It Footer,fr Footer
        Osprey_ReEu.Footer_Links_Repair_And_Replacement("It Footer");    //recaptcha issue while login
       
        
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
		String url = "https://mcloud-na-preprod.osprey.com/it/";
		System.setProperty("url", url);
        Login.signIn();

	}

}
