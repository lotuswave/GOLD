package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_005_Admin_Add_Remove_Up_sells_a_Product {
	String datafile = "Osprey_Admin\\GoldAdminTestData.xlsx";    
	OsperyAdminHelper Admin = new OsperyAdminHelper(datafile,"Upsells");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails"); 
    	Admin.Click_Catalog();
        Admin.Click_Products_Catalogmenu();
        Admin.product_search("AccountDetails");
        Admin.Addproduct_upsells();
        Admin.addselectedproduct();
        Admin.page_Cache("AccountDetails");
        Admin.openwebsite("AccountDetails");
        Admin.verifingHomePage();
        Admin.search_product("AccountDetails");
        Admin.click_product();
        Admin.verifyselectedproduct();
        Admin.back_to_Magento_Admin();
        Admin.Click_Catalog();
        Admin.Click_Products_Catalogmenu();
        Admin.product_search("AccountDetails");
        Admin.Removeproduct_upsells();
        Admin.page_Cache("AccountDetails");
        Admin.openwebsite("AccountDetails");
        Admin.verifingHomePage();
        Admin.search_product("AccountDetails");
        Admin.click_product();
        Admin.verifyremoveproduct();
        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e); 
        } 
    }



    @AfterTest
    public void clearBrowser()
    {
  //    Common.closeAll();

    }


    @BeforeTest
      public void startTest() throws Exception {
    	System.setProperty("configFile", "Osprey_Admin\\config.properties");
        Login.signIn();
    	  	
          Login.signIn();

}

}

