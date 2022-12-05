package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Catalog_017_Catalog_Product_Update {
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Catalog");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	
    	
    	Admin.Admin_signin("AccountDetails");
           Admin.Click_Catalog();
           Admin.Click_Products_Catalogmenu();
           Admin.Search_products("AccountDetails");
           Admin.Click_SearchProduct(); 
           Admin.Update_Name_Price_Stock_Categories("Productupdate");
           Admin.open_website("Address");
           Admin.bottles_headerlinks();
          Admin.click_sortby("AccountDetails");
          Admin.openwebsiteoxo("AccountDetails");
           Admin.POPContainers_headerlinks();
          Admin.click_sortby("AccountDetails");
          Admin.Backto_magento_admin();
           Admin.Actual_Name_Price_Stock_Categories("Productupdate");
      
           
        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e);
        } 
    }



    @AfterTest
    public void clearBrowser()
    {
      Common.closeAll();

    }


    @BeforeTest
      public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
        Login.signIn();
    	  	
          Login.signIn();


      }

    

}


