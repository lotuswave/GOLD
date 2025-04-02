package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_030_Registeruser_Product_Back_in_Stock_Subscription {
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Product_Back_in_Stock_Subscription () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			
			if(Common.getCurrentURL().contains("preprod")) {
			Drybar.search_product("Outof Stock Product"); 
			String amount=Drybar.reg_outofstock_subcription("Outof Stock Product");
			Drybar.My_order_subcribtion("Outof Stock Product");
		    Drybar.remove_outofstock_subcribtion(amount);
			}
			else {
				Drybar.search_product("Prod Outof Stock Product"); 
				String amount=Drybar.reg_outofstock_subcription("Prod Outof Stock Product");
				Drybar.My_order_subcribtion("Prod Outof Stock Product");
			    Drybar.remove_outofstock_subcribtion(amount);
			}
			
			

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
