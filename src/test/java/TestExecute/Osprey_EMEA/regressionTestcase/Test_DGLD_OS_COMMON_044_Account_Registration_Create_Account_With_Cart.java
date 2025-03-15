package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_044_Account_Registration_Create_Account_With_Cart {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Account_Registration_Create_Account_With_Cart () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.simple_addtocart("Simple product");  
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");    
        Osprey_ReEu.click_Createaccount();
        Osprey_ReEu.create_account_With_Product("Create Account");
        Osprey_ReEu.minicart_Checkout();
  
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
