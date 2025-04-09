package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.ES_Footer;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;
  
public class Test_DGLD_OS_COMMON_064_Validating_the_Search_Results_Page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Search");

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
		String url = "https://mcloud-na-preprod.osprey.com/es/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
