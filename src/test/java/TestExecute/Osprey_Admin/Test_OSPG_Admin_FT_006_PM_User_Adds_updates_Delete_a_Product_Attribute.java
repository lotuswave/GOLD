package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_006_PM_User_Adds_updates_Delete_a_Product_Attribute {
	String datafile = "Osprey_Admin\\GoldAdminTestData.xlsx";    
	OsperyAdminHelper Admin = new OsperyAdminHelper(datafile,"Pmanagement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails"); 
        Admin.Click_Stores();
        Admin.Click_Product_Storemenu();
        Admin.Click_Addnewattribute("AccountDetails");
        Admin.Creat_Newattribute("AccountDetails");
        Admin.updates_productAttribute("AccountDetails");
        Admin.Delete_productAttribute("AccountDetails");
       
       
           
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
