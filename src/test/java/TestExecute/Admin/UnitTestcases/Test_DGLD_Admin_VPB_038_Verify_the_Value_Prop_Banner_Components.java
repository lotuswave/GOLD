package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_VPB_038_Verify_the_Value_Prop_Banner_Components {

    String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_the_Value_Prop_Banner_Components() throws Exception {
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
           Admin.editpromocontent_color("promocontent");
           Admin.edit_heading_mobile_valueprop_banner("promocontent");
           Admin.edit_valueprop_banner_one();
           Admin.icon_image_one("promocontent");
           Admin.edit_valueprop_banner_Two();   
           Admin.icon_image_two("promocontent");
           Admin.edit_valueprop_banner_Three();
           Admin.icon_image_galary("promocontent"); 
           Admin.savecontent("promocontent");
           Admin.openwebsite("promocontent");
           Admin.valueprop_website();      
           Admin.clone_valueprop_banner();  
           Admin.vlaueprop_clone_frontend("promocontent"); 
           Admin.deletepage("promocontent");
           Admin.Clearfilter();
           
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