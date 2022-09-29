package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_028_Guest_user_Checkout_Klarna_Payment {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_user_Checkout_Klarna_Payment() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");   
			Hydro.addtocart("Product");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress("AccountDetails");
			Hydro.Kalrna_Payment("Klarna Visa Payment");
          
            
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

	}

}
