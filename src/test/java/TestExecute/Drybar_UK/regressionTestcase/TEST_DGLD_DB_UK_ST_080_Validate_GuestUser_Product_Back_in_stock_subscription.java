package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_080_Validate_GuestUser_Product_Back_in_stock_subscription {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Product_Back_in_stock_subscription () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.search_product("Notify_me_Product");
			Drybar.Notify_me("Notify_me_Product");
			Drybar.Subscribe_to_back_in_stock_notification("Notify_me_Product");
			
		    
            
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();
		

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
