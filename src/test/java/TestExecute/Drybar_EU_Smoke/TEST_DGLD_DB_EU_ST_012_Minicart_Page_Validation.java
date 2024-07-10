package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_EU.GoldDrybarEUHelper;
import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_012_Minicart_Page_Validation {

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarUSHelper Drybar_US = new GoldDrybarUSHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Minicart_Page_Functionality () throws Exception {

		try {
			Drybar_US.Verify_Homepage();
			Drybar_US.HairTools_headerlinks("Hair Tools"); 
			Drybar_US.addtocart("PLP Product");
			Drybar_US.search_product("Configurable Product");
			Drybar_US.Configurable_addtocart("Configurable Product");
			Drybar_US.click_minicart();
            Drybar_US.clickontheproduct_and_image("Configurable Product");
			Drybar_US.minicart_delete("PLP Product");
			Drybar_US.minicart_validation("Configurable Product");
            
 
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
		System.setProperty("configFile", "Drybar_EU\\config.properties");
        Login.signIn();
        Drybar_US.close_add();
        

	}

}