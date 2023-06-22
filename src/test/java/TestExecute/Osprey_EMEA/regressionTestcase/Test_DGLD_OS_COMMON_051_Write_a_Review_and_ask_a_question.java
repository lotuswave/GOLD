package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_051_Write_a_Review_and_ask_a_question {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Review");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Write_a_Review_and_ask_a_question() throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Review product");
        Osprey_ReEu.review("review");
        Osprey_ReEu.Ask_a_question("Ask a question");
//        Osprey_ReEu.filter_validation("Filters");  //need to add after full Implementation
//        Osprey_ReEu.search_filter("Filters");  //need to add after full Implementation
       
        
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
