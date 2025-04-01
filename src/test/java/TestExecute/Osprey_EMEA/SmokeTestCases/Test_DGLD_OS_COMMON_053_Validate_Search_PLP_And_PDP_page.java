package TestExecute.Osprey_EMEA.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_PRODHYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_053_Validate_Search_PLP_And_PDP_page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_PRODHYVA Osprey_ReEu = new OspreyEMEA_PRODHYVA(datafile,"Search");
	OspreyEMEA_PRODHYVA  Osprey_ReEu1 = new OspreyEMEA_PRODHYVA (datafile,"PDP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validated_Search_Result_Functionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Invalid_search_product("Invalid_Product");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.Sort_By("SortBy");
//      Osprey_ReEu.Filter();
        Osprey_ReEu1.Bagpacks_headerlinks("Backpacks & Bags");       
        Osprey_ReEu1.view_PLP_page();
//        Osprey_ReEu1.filter_By("Fliters");
        Osprey_ReEu1.color_validation("PLP Color");
        Osprey_ReEu1.price_filter_validation("PLP Color");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu1.Simple_PDP("Simple product");
        Osprey_ReEu1.search_product("Product");  
        Osprey_ReEu1.Configurable_PDP("Product");
         
    
        
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