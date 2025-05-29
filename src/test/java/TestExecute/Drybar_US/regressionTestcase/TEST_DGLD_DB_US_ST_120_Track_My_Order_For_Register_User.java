package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_120_Track_My_Order_For_Register_User {


	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Track_My_Order");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Track_My_Order_For_Register_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.register_userorder_status();
			Drybar.view_order();
			Drybar.search_E2E_Completeorder();
			Drybar.Reg_shipment_invoice();
			
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();
		

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
  //      Drybar.close_add();
        

	}

}
