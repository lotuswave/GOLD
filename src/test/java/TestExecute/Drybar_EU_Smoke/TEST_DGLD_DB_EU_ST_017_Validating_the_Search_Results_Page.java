package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_EU.GoldDrybarEUHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_017_Validating_the_Search_Results_Page {

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarEUHelper Drybar_EU = new GoldDrybarEUHelper(datafile,"Search");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_Search_Results_Page () throws Exception {

		try {
			Drybar_EU.Verify_Homepage();
			Drybar_EU.Invalid_search_product("Invalid_Search");
			Drybar_EU.search_product("Valid_Search");  
			Drybar_EU.Sort_By("SortBy");
			Drybar_EU.Filter();
			Drybar_EU.addtocart("Valid_Search");
            
 
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
        Drybar_EU.close_add();
        

	}

}