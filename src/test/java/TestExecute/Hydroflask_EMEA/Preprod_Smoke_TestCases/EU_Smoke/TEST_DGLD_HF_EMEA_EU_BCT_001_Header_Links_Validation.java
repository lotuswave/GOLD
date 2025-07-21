
package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_EU_BCT_001_Header_Links_Validation {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"HeaderLinks");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.New_Color_Destination("Colors");	
	//	    Hydro.travel_Bottles_validation("travel Bottles");   //add zero product 
		    Hydro.Travel_Tumbler_validation("Travel Tumbler");			  
	//		Hydro.shop_by_Color_validation("Shop by Color");          
            Hydro.bottles_validation("Bottle and drinkware");	
        	Hydro.Coolers_validation("Coolers");                                 
    //     	Hydro.kitchenware_validation("kitchenware");          	
            Hydro.Accessories_validation("Accessories");           
    //        Hydro.Collections_validation("Collections");            //working fine needsto commit  and add one zero product
			Hydro.Explore_Validation("Explore Links");                
			Hydro.featured_validation("Featured");                       
			Hydro.New_Color_Destination("Colors");
			Hydro.featured_Shopby_Collections("Shopby Collections");
			
//			Hydro.Customize_validation("Customize");			
//			Hydro.Holiday_shop_validation("Holiday Sale"); 
			
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
		String url="https://mcloud-na-preprod.hydroflask.com/eu";
		System.setProperty("url", url);
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
