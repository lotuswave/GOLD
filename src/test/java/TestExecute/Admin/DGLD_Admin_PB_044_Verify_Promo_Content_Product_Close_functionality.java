package TestExecute.Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class DGLD_Admin_PB_044_Verify_Promo_Content_Product_Close_functionality {
	String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin=new Adminhelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Promo_Content_Product_Close_functionality() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA();
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.dragndrop_Promo_Content();
           Admin.close_promo_page();
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
    	System.setProperty("configFile", "Oxo\\config.properties");
          Login.signIn();


      }

    

}