package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_090_Validating_the_product_video_in_PDP_page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_product_video_in_PDP_page () throws Exception {

		try {
//			String currentUrl = Common.getCurrentURL().trim();
//			System.out.println("Current URL: " + currentUrl);
			Hydro.verifingHomePage();
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.PDP_video_validation("Product");
//			if(currentUrl.contains("https://www.hydroflask.com/")) {
//				System.out.println("Executing in Prod");
//				Hydro.search_product("Video Product_Prod");      
//				Hydro.Prod_PDP_video_validation("Video Product_Prod"); 
//			}
//			else {
//				System.out.println("Executing in Pre-Prod");
//				Hydro.bottles_headerlinks("Bottles & Drinkware"); 
//				Hydro.PDP_video_validation("Product");
//			}
			
		

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
