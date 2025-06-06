package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.SW_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA ;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_SW_053_Validate_PLP_page {

	String datafile = "Osprey_EMEA//GoldOspreySE_SV.xlsx";
	OspreyEMEA_HYVA  Osprey_ReEu = new OspreyEMEA_HYVA (datafile,"PDP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_PLP_Page () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu.view_PLP_page();
       // Osprey_ReEu.filter_By("Fliters");
        Osprey_ReEu.color_validation("PLP Color");
        Osprey_ReEu.price_filter_validation("PLP Color");
 
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
		String url = "https://mcloud-na-preprod.osprey.com/se_sv/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
