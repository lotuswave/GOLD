package TestExecute.Osprey_US.ContentTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;
  
public class Test_DGLD_OS_US_Content_002_Validating_the_Search_Results_Page {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Search");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validated_Search_Result_Functionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Invalid_search_product("Invalid_Product");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.Sort_By("SortBy");
        Osprey_ReEu.Filter();
         
    
        
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
