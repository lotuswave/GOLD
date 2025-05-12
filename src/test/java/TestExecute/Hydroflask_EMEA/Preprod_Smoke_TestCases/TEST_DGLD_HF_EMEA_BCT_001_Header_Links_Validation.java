
package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_BCT_001_Header_Links_Validation {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"HeaderLinks");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.New_Arrivals_validation("New Arrivals");
		    Hydro.travel_Bottles_validation("travel Bottles");
		    Hydro.Travel_Tumbler_validation("Travel Tumbler");			
			Hydro.shop_by_Color_validation("Shop by Color");
            Hydro.bottles_validation("Bottle and drinkware");			
        	Hydro.Coolers_validation("Coolers");  
         	Hydro.kitchenware_validation("kitchenware");
            Hydro.Accessories_validation("Accessories");
            Hydro.Collections_validation("Collections");        
			Hydro.Explore_Validation("Explore Links");
//			Hydro.Customize_validation("Customize");
			
			 // Hydro.New_Color_Destination("Colors");
		     //  Hydro.featured_validation("Featured"); 
						
			// Hydro.Holiday_shop_validation("Holiday Sale"); 
			
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
