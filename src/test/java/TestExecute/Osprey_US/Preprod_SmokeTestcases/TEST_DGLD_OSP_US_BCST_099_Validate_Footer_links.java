package TestExecute.Osprey_US.Preprod_SmokeTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_US_BCST_099_Validate_Footer_links {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Footer Links");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Footer_Links_Funtionality () throws Exception {

		try {
			    Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.Customer_Links("Customer");
		        Osprey_ReEu.Footer_breadcrumbs_validation("Breadcrumbs");
		        Osprey_ReEu.Company_and_terms_links("Footer");
		        Osprey_ReEu.Footer_Links_Resources("Footer");
		        Osprey_ReEu.Footer_Links_BrandTeam("Footer");           
		        Osprey_ReEu.Footer_Links_Repari_And_Replacement("Footer");
		        Osprey_ReEu.AMG_chain_Act_Access_Cookie(); 
        
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
