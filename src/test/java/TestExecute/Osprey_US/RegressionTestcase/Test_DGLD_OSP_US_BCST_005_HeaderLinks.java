
package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_BCST_005_HeaderLinks {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Header");

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
			Osprey_ReEu.header_Holiday_Gift_Guide("Featured");        //no need include in prod
			Osprey_ReEu.Bagpack_ShopAll("ShopAll");   
			Osprey_ReEu.Travel_ShopAll("TravelShopAll");                 
			Osprey_ReEu.header_Shopbyactivity("Shop by Activity");       
			Osprey_ReEu.header_Shopbycollection("Shop by Collections"); 
			Osprey_ReEu.Featured_ShopAll("FeaturedShopAll");      
			Osprey_ReEu.header_Explore("Explore");
			Osprey_ReEu.header_Explore_2("Explore");
			
        
        
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
