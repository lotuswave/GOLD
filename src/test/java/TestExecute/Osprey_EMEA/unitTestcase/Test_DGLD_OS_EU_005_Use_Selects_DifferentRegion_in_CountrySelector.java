package TestExecute.Osprey_EMEA.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.GoldOspreyEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_005_Use_Selects_DifferentRegion_in_CountrySelector {
	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	GoldOspreyEMEAHelper Osprey_Eu = new GoldOspreyEMEAHelper(datafile,"CreateAccount");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Test_DGLD_OS_EU_008_Use_Selects_DifferentRegion_in_CountrySelecto () throws Exception {

		try {
      Osprey_Eu.verifingHomePage();
      Osprey_Eu.clickcountryselector();
      Osprey_Eu.Selectscountries_CountrySelector("CountrySelector");     
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
