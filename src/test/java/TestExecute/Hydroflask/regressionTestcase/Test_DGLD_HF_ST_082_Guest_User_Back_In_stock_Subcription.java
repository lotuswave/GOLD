package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_082_Guest_User_Back_In_stock_Subcription {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Outofstock");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Back_In_stock_Subcription () throws Exception {
  
		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Outofstock Prod");     
			Hydro.outofstock_subcription("Outofstock Product");
			
			

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
