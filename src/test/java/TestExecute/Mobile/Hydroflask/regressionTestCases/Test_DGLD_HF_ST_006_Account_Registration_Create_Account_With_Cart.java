package TestExecute.Mobile.Hydroflask.regressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_006_Account_Registration_Create_Account_With_Cart {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper_Mobile Hydro = new GoldHydroHelper_Mobile(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Registration_Create_Account_With_Cart () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");  
			Hydro.Configurable_addtocart_pdp("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
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
		String device=System.getProperty("dev","IOS");
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		if(device.equalsIgnoreCase("ios")) {
			System.setProperty("configFile", "Hydroflask/mobile_config_ios.properties");
		}
		  Login.mobilesignIn(device);
        Hydro.close_add();

	}

}
