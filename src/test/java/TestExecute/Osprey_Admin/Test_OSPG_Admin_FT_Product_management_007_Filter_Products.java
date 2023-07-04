package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_Product_management_007_Filter_Products {
	String datafile = "Osprey_Admin\\GoldAdminTestData.xlsx";    
	OsperyAdminHelper Admin = new OsperyAdminHelper(datafile,"Pmanagement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails"); 
        Admin.Click_Catalog();
        Admin.Click_Products_Catalogmenu();
        Admin.Click_Filters();
        Admin.Click_SKU("AccountDetails");
     
           
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
    	System.setProperty("configFile", "Osprey_Admin\\config.properties");
        Login.signIn();
    	  	
          Login.signIn();

}

}
