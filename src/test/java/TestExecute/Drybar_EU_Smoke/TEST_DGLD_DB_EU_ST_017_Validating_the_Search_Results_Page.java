package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_017_Validating_the_Search_Results_Page {

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Search");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_Search_Results_Page () throws Exception {

		try {
			Drybar.Verify_Homepage();
			Drybar.Invalid_search_product("Invalid_Search");
			Drybar.search_product("Valid_Search");  
			Drybar.Sort_By("SortBy");
			Drybar.Filter();
			Drybar.addtocart("Valid_Search");
            
 
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
        Drybar.close_add();
        

	}

}