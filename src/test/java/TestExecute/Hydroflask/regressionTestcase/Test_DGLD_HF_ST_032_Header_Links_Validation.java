package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_032_Header_Links_Validation {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"HeaderLinks");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links_Validation () throws Exception {

		try {
			Hydro.verifingHomePage();
            Hydro.bottles_validation("Bottle and drinkware");
            Hydro.Coolers_validation("Coolers");  
            Hydro.kitchenware_validation("kitchenware");
            Hydro.Accessories_validation("Accessories");
            Hydro.featured_validation("Featured");
//            Hydro.New_Color_Destination("Colors");   
            Hydro.customize_shopall("Shop All");
			Hydro.Explore_Validation("Explore Links");
			Hydro.image_button("Shop All");
			
			
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
