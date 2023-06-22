package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_045_Minicart_Validation {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Minicart");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Create_account_Funtionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Product");      
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.search_product("ConfigurableProduct");      
        Osprey_ReEu.addtocart("ConfigurableProduct");
        Osprey_ReEu.click_minicart();
        Osprey_ReEu.clickontheproduct_and_image("Product");
        Osprey_ReEu.minicart_freeshipping();
        Osprey_ReEu.minicart_delete("Product");
        Osprey_ReEu.minicart_product_close();
        Osprey_ReEu.minicart_validation("Product Qunatity");
        
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
