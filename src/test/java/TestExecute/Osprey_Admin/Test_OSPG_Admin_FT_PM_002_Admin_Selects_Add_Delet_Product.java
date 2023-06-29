package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_PM_002_Admin_Selects_Add_Delet_Product {
	
	
	

	String datafile = "Osprey_Admin\\GoldAdminTestData.xlsx";
	OsperyAdminHelper OspreyAdmin = new OsperyAdminHelper(datafile,"Pmanagement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Admin_Selects_Add_Delet_Produt () throws Exception {

		try {
        
      OspreyAdmin.Admin_signin("AccountDetails");
       OspreyAdmin.Click_Catalog();
       OspreyAdmin.Click_Products_Catalogmenu();
        OspreyAdmin.Search_products("Product");
        OspreyAdmin.Click_Addproduct();
        OspreyAdmin.Add_New_product("Newproduct");
        OspreyAdmin.product_Reindex("ProductReindex");
       OspreyAdmin.Cache_Management("CacheManagement");
       OspreyAdmin.open_Website("frontend");
       OspreyAdmin.search_product("Searchproduct");
      OspreyAdmin.Backto_magento_Cache_admin();
       OspreyAdmin.Click_Catalog();
       OspreyAdmin.Click_Products_Catalogmenu();
       OspreyAdmin.Search_products("AdminProduct");
      OspreyAdmin.Delete_product("AdminProduct");
       OspreyAdmin.open_Website("frontend");
      
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
