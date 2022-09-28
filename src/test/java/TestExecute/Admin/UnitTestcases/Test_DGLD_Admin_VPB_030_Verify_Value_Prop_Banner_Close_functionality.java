package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_VPB_030_Verify_Value_Prop_Banner_Close_functionality {

    String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Value_Prop_Banner_Close_functionality() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Promo_Content();
           Admin.dragndrop_valueprop_Banner();
           Admin.edit_valueprop_banner();
           Admin.close_valueprop_page();
           Admin.edit_valueprop_banner_one();
           Admin.close_individual_page_one();
           Admin.edit_valueprop_banner_Two();
           Admin.close_individual_page_Two();
           Admin.edit_valueprop_banner_Three();
           Admin.close_individual_page_Three();
          
           
           
           
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


      }
   

}