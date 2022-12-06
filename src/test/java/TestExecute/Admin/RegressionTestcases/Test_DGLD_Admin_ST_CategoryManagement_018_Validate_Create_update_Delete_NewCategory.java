package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CategoryManagement_018_Validate_Create_update_Delete_NewCategory {

	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Category");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_update_Delete_NewCategory() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.click_catlog();
			Admin.click_categories();
			Admin.Verify_CTALinks();
			Admin.categorylist();
			Admin.category_content();
			Admin.Validate_Content();
			Admin.click_Display();
			Admin.click_search_engine();
			Admin.products_category();
			Admin.Click_design();
			Admin.click_Add_Root_Category();
			Admin.savecategory("Hydroflask");
			Admin.deletecategory();

			Admin.delete_ExistingCategory("Hydroflask");
			Admin.Click_Shop("Hydroflask");
			Admin.Create_Newcategory("Hydroflask");
			Admin.Create_Newsubcategory("Hydroflask");
			Admin.open_website("Hydroflask");
			Admin.Validate_Category_subcategory_frontend("Hydroflask");
			Admin.Navigate_Adminpage();
			Admin.Update_SubCategory("Hydroflask");
			Admin.Flush_Magneto_cache("Magentocache");

			Admin.open_Website("Hydroflask");
			Admin.Validate_Category_Update_Frontend("Hydroflask");
			Admin.Navigate_Adminpage();
			Admin.click_catlog();
			Admin.click_categories();
			Admin.delete_ExistingCategory("Hydroflask");
			Admin.Flush_Magneto_cache("Magentocache");

			Admin.open_Website("Hydroflask");
			Admin.Validate_deletcategory_Frontend("Hydroflask");

			Admin.Navigate_Adminpage();
			Admin.click_catlog();
			Admin.click_categories(); 
			Admin.delete_ExistingCategory("OXO");
			Admin.Click_Shop("OXO");
			Admin.Create_Newcategory("OXO");
			Admin.Create_Newsubcategory("OXO");
			Admin.open_website("OXO");
			Admin.Validate_Category_subcategory_frontend("OXO");
			Admin.Navigate_Adminpage();
			Admin.Update_SubCategory("OXO");
			Admin.Flush_Magneto_cache("Magentocache");
			Admin.open_Website("OXO");
			Admin.Validate_Category_Update_Frontend("OXO");
			Admin.Navigate_Adminpage();
			Admin.click_catlog();
			Admin.click_categories();
			Admin.delete_ExistingCategory("OXO");
			Admin.Flush_Magneto_cache("Magentocache");
			Admin.open_Website("OXO");
			Admin.Validate_deletcategory_Frontend("OXO");

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

		Login.signIn();

	}

}
