package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_PB_050_Verify_the_Promo_Block_Components_Background_Video {

    String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_the_Promo_Block_Components_Background_Video() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Promo_Content();
           Admin.dragndrop_Promo_Content();
           Admin.editcontent();
           Admin.editpromocontent_color("promocontent");
           Admin.editpromocontent_video("promocontent");
           Admin.editpromocontent_fallback_image();
           Admin.savecontent("promocontent");
           Admin.openwebsite("promocontent");
           Admin.video_promo_component();
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