package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_004_Minicart_Validation {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Minicart_Validation() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.headerlinks("QA_Testing");      
			Hydro.addtocart("Product");
			Hydro.headerlinks("Bottles & Drinkware"); 
			Hydro.addtocart_pdp("Product");
			Hydro.click_minicart();
			Hydro.minicart_delete("Product");
			Hydro.minicart_validation("Product Qunatity");
			
			
			
			
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

	}

}
