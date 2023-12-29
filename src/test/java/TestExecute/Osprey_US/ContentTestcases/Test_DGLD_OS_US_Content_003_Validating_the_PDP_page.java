package TestExecute.Osprey_US.ContentTestcases;
  
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;
  
public class Test_DGLD_OS_US_Content_003_Validating_the_PDP_page {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"AR-Spanish_PDP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_PDP_Page () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();  
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu.Simple_PDP("Simple product");
        Osprey_ReEu.search_product("configurable product");  
        Osprey_ReEu.Configurable_PDP("configurable product");
       
  
        
 
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
