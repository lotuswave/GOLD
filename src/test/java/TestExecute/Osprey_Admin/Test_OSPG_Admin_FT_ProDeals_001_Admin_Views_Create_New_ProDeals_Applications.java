package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_ProDeals_001_Admin_Views_Create_New_ProDeals_Applications {
	
	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";
	OsperyAdminHelper OspreyEU_Admin = new OsperyAdminHelper(datafile,"Dataset");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void ProDeal_Application_Accept() throws Exception {

		try {
        OspreyEU_Admin.Admin_signin("AccountDetails");
        OspreyEU_Admin.Customers();
        OspreyEU_Admin.ProDeal_Application();
        OspreyEU_Admin.Click_create_new_prodeal();
        OspreyEU_Admin.Prodeal_program_application_form("ProDealForm");
       
        
        
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
		System.setProperty("configFile", "Osprey_Admin\\config.properties");
        Login.signIn();
        

	}

}



