package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_005_HeaderLinks {

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
        Osprey_ReEu.header_Accessories("Accessories");         //While Executing EU Please comment this line and Execute it.
        Osprey_ReEu.header_Featured("Featured");
        Osprey_ReEu.Bagpack_ShopAll("ShopAll");
        Osprey_ReEu.Travel_ShopAll("TravelShopAll");                 //While Executing EU please comment this line and execute it
        Osprey_ReEu.header_Shopbyactivity("Shop by Activity");       
        Osprey_ReEu.header_Shopbycollection("Shop by Collections"); 
        Osprey_ReEu.Featured_ShopAll("FeaturedShopAll");      
        Osprey_ReEu.image_ShopAll("ImageShopAll");     // image shop all is not working in preprod
        Osprey_ReEu.header_sale();
        Osprey_ReEu.header_Explore("Explore");       //While Executing EU please comment this line and Execute it
        
        
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
