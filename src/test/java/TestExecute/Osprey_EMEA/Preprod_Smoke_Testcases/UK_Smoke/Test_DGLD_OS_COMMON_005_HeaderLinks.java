package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.UK_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_005_HeaderLinks {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Header");

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
	//	      Osprey_ReEu.Bagpack_ShopAll("ShopAll");
		      Osprey_ReEu.header_Shopbyactivity("Shop by Activity");       
		      Osprey_ReEu.header_Shopbycollection("Shop by Collections"); 
		      Osprey_ReEu.Featured_ShopAll("FeaturedShopAll");                                                     
		      Osprey_ReEu.header_Icons("Icons");
		      Osprey_ReEu.header_Shopby_Litres("Shop by Litres");
		      Osprey_ReEu.header_Explore("Explore");
		      Osprey_ReEu.header_New_Season();  
	
		        
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
