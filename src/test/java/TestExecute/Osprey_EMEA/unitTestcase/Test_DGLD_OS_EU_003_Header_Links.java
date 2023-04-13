package TestExecute.Osprey_EMEA.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.GoldOspreyEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_003_Header_Links {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	GoldOspreyEMEAHelper Osprey_Eu = new GoldOspreyEMEAHelper(datafile,"Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links_Functionality () throws Exception {

		try {
        Osprey_Eu.verifingHomePage();
        Osprey_Eu.header_OutdoorPacks("Outdoor Packs");
        Osprey_Eu.header_KidsPacks("Kids Packs Carriers");
        Osprey_Eu.header_DayPacks("Day Packs");
        Osprey_Eu.header_Travel("Travel");
        Osprey_Eu.header_Accessories("Accessories");
        Osprey_Eu.header_Featured("Featured");
        Osprey_Eu.header_ShopAll("ShopAll");
			
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
