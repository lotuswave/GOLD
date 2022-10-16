package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CategoryManagement_019_Sceduledcategoryupdate {

	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Category");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Admin_validate() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Click_Catalog();
			Admin.click_categories();
			Admin.Click_Shop("Hydroflask");
			Admin.delete_ExistingCategory("Hydroflask");
			Admin.Create_Newcategory("Hydroflask");
			Admin.Create_Newsubcategory("Hydroflask");
			Admin.Configure_scheduleupdate("Hydroflask");
			//Admin.addproducts("Hydroflask");
			Admin.open_Website("Hydroflask");
			Admin.AcceptAll();
			Admin.ClosADD();
			Admin.Validate_Category_subcategory_frontend("Hydroflask");
			Admin.ValidateScedulecategoryupdate("Hydroflask");
			Admin.Click_Shop("OXO");
			Admin.delete_ExistingCategory("OXO");
			Admin.Create_Newcategory("OXO");
			Admin.Create_Newsubcategory("OXO");
			Admin.Configure_scheduleupdate("OXO");
			//Admin.addproducts("Hydroflask");
			Admin.open_Website("OXO");
			Admin.AcceptAll();
			Admin.ClosADD();
			Admin.Validate_Category_subcategory_frontend("OXO");
			Admin.ValidateScedulecategoryupdate("OXO");
		
		

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
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}

}
