package TestExecute.Osprey_US.ContentTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_Content_007_Validate_PLP_Page {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"AR-English_PDP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_PLP_Page () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu.view_PLP_page();
        Osprey_ReEu.filter_By("Fliters");
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
