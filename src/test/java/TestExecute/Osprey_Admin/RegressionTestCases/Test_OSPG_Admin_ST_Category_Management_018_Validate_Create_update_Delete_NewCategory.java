package TestExecute.Osprey_Admin.RegressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_ST_Category_Management_018_Validate_Create_update_Delete_NewCategory {

	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";

	OsperyAdminHelper Osprey_Admin = new OsperyAdminHelper(datafile, "Category");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_update_Delete_NewCategory() throws Exception {
		try {
			
			
			Osprey_Admin.Admin_signin("AccountDetails");
			Osprey_Admin.click_catlog();
			Osprey_Admin.click_categories();
			Osprey_Admin.Verify_CTALinks();
			Osprey_Admin.categorylist();
			Osprey_Admin.category_content();
			Osprey_Admin.Validate_Content();
			Osprey_Admin.click_Display();
			Osprey_Admin.click_search_engine();
			Osprey_Admin.products_category();
			Osprey_Admin.Click_design();
			
//Created Root Category and deleting
		    Osprey_Admin.click_Add_Root_Category();
			Osprey_Admin.savecategory("Osprey_EU");
			Osprey_Admin.deletecategory();
			
//Created category and validated in frontEnd
		    Osprey_Admin.Click_Defaultcategory("Osprey_EU");
		    Osprey_Admin.Create_NewMaincategory("Osprey_EU");
			Osprey_Admin.Create_Newcategory("Osprey_EU");
		    Osprey_Admin.Create_Newsubcategory("Osprey_EU");
			Osprey_Admin.open_Website("Osprey_EU");
			Osprey_Admin.Validate_Category_subcategory_frontend("Osprey_EU");
			
//Update the subcategory and Validated in frontEnd			
			Osprey_Admin.Navigate_Adminpage();
			Osprey_Admin.Update_SubCategory("Osprey_EU");
			Osprey_Admin.Flush_Magneto_cache("Magentocache");
			Osprey_Admin.open_Website("Osprey_EU");
			Osprey_Admin.Validate_Category_Updatesubcategory_frontend("Osprey_EU");
			
//Deleted the created category and Validated in frontEnd			
			Osprey_Admin.Navigate_Adminpage();
			Osprey_Admin.click_catlog();
			Osprey_Admin.click_categories();
			Osprey_Admin.delete_ExistingCategory("Osprey_EU");
			Osprey_Admin.Flush_Magneto_cache("Magentocache");
			
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

		Login.signIn();

	}

}
