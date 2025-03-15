package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_151_Validating_the_Search_Results_Page {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Search");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_Search_Results_Page  () throws Exception {

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
