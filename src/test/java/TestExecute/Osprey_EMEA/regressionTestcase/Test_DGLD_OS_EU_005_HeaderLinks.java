package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_005_HeaderLinks {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Header_Links_Functionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.header_OutdoorPacks("Outdoor Packs");
        Osprey_ReEu.header_KidsPacks("Kids Packs Carriers");
        Osprey_ReEu.header_DayPacks("Day Packs");
        Osprey_ReEu.header_Travel("Travel");
        Osprey_ReEu.header_Accessories("Accessories");
        Osprey_ReEu.header_Featured("Featured");
        Osprey_ReEu.header_ShopAll("ShopAll");
        
        
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
