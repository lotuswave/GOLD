package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_COMMON_126_Product_Registration_form {
	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx"; 
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Forms");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verify_product_registration_form_submission () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_Product_Registration();;
        Osprey_ReEu.product_Registration("ProductRegistration");
        
        
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
