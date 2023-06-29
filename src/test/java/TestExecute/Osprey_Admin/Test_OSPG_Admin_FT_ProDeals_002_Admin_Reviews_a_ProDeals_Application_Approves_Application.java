package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_ProDeals_002_Admin_Reviews_a_ProDeals_Application_Approves_Application {
	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";
	OsperyAdminHelper OspreyEU_Admin = new OsperyAdminHelper(datafile,"Dataset");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void ProDeal_Application_Accept() throws Exception {

		try {
        OspreyEU_Admin.Admin_signin("AccountDetails");
        OspreyEU_Admin.Customers();
        OspreyEU_Admin.ProDeal_Application();
        OspreyEU_Admin.Select_CustomerProdeal_Application("ProDeal");
        OspreyEU_Admin.Accept_ProDeal_Application();
        OspreyEU_Admin.Click_Save();
        
        
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
