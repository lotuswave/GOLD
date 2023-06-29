package TestExecute.Osprey_Admin.RegressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_ST_Category_Management_019_Sceduled_Category_Update {

	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";

	OsperyAdminHelper Osprey_Admin = new OsperyAdminHelper(datafile, "Category");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Sceduled_categoryupdate() throws Exception {

		try {
			
		   Osprey_Admin.Admin_signin("AccountDetails");
			Osprey_Admin.click_catlog();
			Osprey_Admin.click_categories();
			Osprey_Admin.Click_Defaultcategory("Osprey_EU");
			

			Osprey_Admin.Create_NewMaincategory("Osprey_EU");
			Osprey_Admin.Create_Newcategory("Osprey_EU");
		    Osprey_Admin.Create_Newsubcategory("Osprey_EU");
			Osprey_Admin.Configure_scheduleupdate("Osprey_EU");
			Osprey_Admin.open_Website("Osprey_EU");
			Osprey_Admin.Validate_Category_subcategory_frontend("Osprey_EU");
			Osprey_Admin.ValidateScedulecategoryupdate("Osprey_EU"); 
			
			Osprey_Admin.open_Website("Osprey_EU");
			Osprey_Admin.Validate_deletcategory_Frontend("Osprey_EU");
			
			
			
			
		
		

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
