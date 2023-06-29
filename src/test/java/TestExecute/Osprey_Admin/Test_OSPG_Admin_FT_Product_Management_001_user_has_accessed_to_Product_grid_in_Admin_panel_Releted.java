package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_Product_Management_001_user_has_accessed_to_Product_grid_in_Admin_panel_Releted {

	String datafile = "Osprey_Admin\\GoldAdminTestData.xlsx";
	OsperyAdminHelper OspreyAdmin = new OsperyAdminHelper(datafile,"Pmanagement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Create_account_Funtionality () throws Exception {

		try {
       
        OspreyAdmin.Admin_signin("AccountDetails");
       OspreyAdmin.Click_Catalog();
        OspreyAdmin.Click_Products_Catalogmenu();
        OspreyAdmin.Search_products("Product");
        OspreyAdmin.Click_SearchProduct();
        OspreyAdmin.PaginationCTA();
        OspreyAdmin.Product_listing_page_size("Custom");
        OspreyAdmin.Click_Edit();
        OspreyAdmin.Adds_Related_Product("Product");
        OspreyAdmin.product_Reindex("ReletedReindex");
        OspreyAdmin.Cache_Management("CacheManagement");
        OspreyAdmin.open_Website("frontend");
        OspreyAdmin.search_product("Product");
        OspreyAdmin.PDP_Releted_search("Product");
       OspreyAdmin.Backto_magento_Cache_admin();
        OspreyAdmin.Click_Catalog();
        OspreyAdmin.Click_Products_Catalogmenu();
        OspreyAdmin.Click_Edit();
        OspreyAdmin.delete_Related_Product("Deleteproduct");
        OspreyAdmin.product_Reindex("ReletedReindex");
        OspreyAdmin.Cache_Management("CacheManagement");
        OspreyAdmin.open_Website("frontend");
        OspreyAdmin.search_product("Product");
        OspreyAdmin.PDP_deleteReleted_search("Product");
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
//	Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Osprey_Admin\\config.properties");
        Login.signIn();
        

	}

}
