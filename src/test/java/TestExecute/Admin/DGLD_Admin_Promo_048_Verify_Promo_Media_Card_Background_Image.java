package TestExecute.Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class DGLD_Admin_Promo_048_Verify_Promo_Media_Card_Background_Image {

    String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin=new Adminhelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Promo_Media_Card_Background_Image() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA();
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Promo_Content();
           Admin.dragndrop_Promo_Content();
           Admin.editcontent();
           Admin.editpromocontent_color("promocontent");
           Admin.editpromocontent_image();
           Admin.allbackground("promocontent");
           Admin.savecontent("promocontent");
           Admin.openwebsite("promocontent");
           Admin.website_verification();

        //   Admin.clonepage();
//           Admin.deletepage();
        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e);
        } 
    }



    @AfterTest
    public void clearBrowser()
    {
//        Common.closeAll();

    }


    @BeforeTest
      public void startTest() throws Exception {

          Login.signIn();


      }

    

}