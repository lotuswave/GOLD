package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_010_Find_a_Dealer {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Store_Locator_Funtionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Find_a_delear();
        Osprey_ReEu.click_Retailer();
        Osprey_ReEu.verifingRetailerHours();
        Osprey_ReEu.verifingRetailerBrowser();
        Osprey_ReEu.Validate_store_sidebar();
        Osprey_ReEu.CLick_Usemylocation();
        Osprey_ReEu.Validate_AvailableRetailers();
        Osprey_ReEu.Validate_retailerlocations();  //need to add locations
        Osprey_ReEu.Click_Instock();
        Osprey_ReEu.selectproduct("Daylite Plus");
        
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
