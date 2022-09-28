package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_Page_Builder_083_Verify_Edit_Testimonials_Product_Card_Components_Content {

    String datafile = "Admin//AdminTestData.xlsx";    
    Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Edit_Testimonials_Product_Card_Components_Content() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Testimonials();
           Admin.dragndrop_Testimonials();
           Admin.edit_Testimonial_one();
           Admin.Testimonial_author_one("Author one");
           Admin.edit_Testimonial_Two();
           Admin.Testimonial_author_Two("Author Two");
           Admin.edit_Testimonial_Three();
           Admin.Testimonial_author_Three("Author Three");
           Admin.savecontent("promocontent");
           Admin.openwebsite("promocontent");
           Admin.Testimonial_frontend("Author one");
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