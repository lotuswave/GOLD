
package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.FR_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_FR_BCT_001_Header_Links_Validation {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"HeaderLinks_FR");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.New_Color_Destination("Colors");	          
            Hydro.bottles_validation("Bottle and drinkware");	   
        	Hydro.Coolers_validation("Coolers");                   	
            Hydro.Accessories_validation("Accessories"); 
			Hydro.Explore_Validation("Explore Links");                
			Hydro.featured_validation("Featured");                       
			Hydro.featured_Shopby_Collections("Shopby Collections");
			Hydro.featured_Shopby_Activity("Shopby Activity");
			Hydro.Shop_Shopall("Shop All");
			Hydro.Featured_ShopAll("Featured shopall");
			
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
		String url="https://www.hydroflask.com/fr";
		System.setProperty("url", url);
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
