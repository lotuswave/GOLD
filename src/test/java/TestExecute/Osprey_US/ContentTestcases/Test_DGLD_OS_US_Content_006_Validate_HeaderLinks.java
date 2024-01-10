package TestExecute.Osprey_US.ContentTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_Content_006_Validate_HeaderLinks {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"EN Content Header");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Header_Links_Functionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.header_OutdoorPacks("Outdoor Packs");
        Osprey_ReEu.header_KidsPacks("Kids Packs Carriers");   
        Osprey_ReEu.header_DayPacks("Day Packs");
        Osprey_ReEu.header_Travel("Travel");
        Osprey_ReEu.header_Luggage("Travel");
        Osprey_ReEu.header_Accessories("Accessories");         
        Osprey_ReEu.header_Featured("Featured");  
        Osprey_ReEu.Bagpack_ShopAll("ShopAll");   
        Osprey_ReEu.Travel_ShopAll("TravelShopAll");                 
        Osprey_ReEu.header_Shopbyactivity("Shop by Activity");       
        Osprey_ReEu.header_Shopbycollection("Shop by Collections"); 
        Osprey_ReEu.Featured_ShopAll("FeaturedShopAll");      
        Osprey_ReEu.header_Explore("Explore"); 
        
        
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
