package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_025_Article_links_validation {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Artical Links");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_Artical_Links_Funtionality () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.Artical_Links("ArticalLinks");
			
			

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
