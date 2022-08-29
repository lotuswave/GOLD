package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_006_Account_Registration_Create_Account_With_Cart {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Registration_Create_Account_With_Cart () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.headerlinks("QA_Testing");      
			Hydro.addtocart("Product");  
			Hydro.headerlinks("Bottles & Drinkware"); 
			Hydro.addtocart_pdp("Product");
			String minicart=Hydro.minicart_items();
			Hydro.click_Createaccount();
			Hydro.create_account("AccountDetails");
			Hydro.minicart_products(minicart);
			

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
