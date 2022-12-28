package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Page_Builder_007_Verify_Edit_Testimonials_Product_Card_Components_Content {

    String datafile = "Admin//GoldAdminTestData.xlsx";
    GoldAdminHelper Admin = new GoldAdminHelper(datafile,"TestmonialProductcard");


  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Admin_Verify_Edit_Testimonials_Product_Card_Components_Content() throws Exception {


      try {
    	  Admin.Admin_signin("AccountDetails");
          Admin.click_content();
          Admin.pages();
          Admin.newpageCTA("TestmonialProductcard");
          Admin.Contentpage();
          Admin.hot_elements();
          Admin.Testimonials_Product_Carousel();
          Admin.dragndrop_Testimonials_Product_Carousel();
        
          Admin.edit_Testimonial_one();          
          Admin.SKUvalue("Author one");
          Admin.Content_Dark("Author one");
          Admin.Editcardtile_color("Author one");
          Admin.CSSclassvalue("Author one");
          Admin.Configure_padding_marins("Author one");
          Admin.Editandsavepage();
          
        
          Admin.edit_Testimonial_Two();
         // Admin.SKUvalue("Author Two");
         
          Admin.Content_Light("Author Two");
          Admin.Editcardtile_color("Author Two");
          Admin.CSSclassvalue("Author Two");
          Admin.Configure_padding_marins("Author Two");
          Admin.backgroundicon_image_galary("Author Two");
          
          
          Admin.edit_Testimonial_Three();
          Admin.SKUvalue("Author Three");
          Admin.Content_Dark("Author Three");
          Admin.Editcardtile_color("Author Three");
          Admin.CSSclassvalue("Author Three");
          Admin.Configure_padding_marins("Author Three");
          Admin.Editandsavepage();
          Admin.edit_Testimonial("TestmonialProductcard");
          
          
          
          Admin.savecontent("TestmonialProductcard");
          Admin.page_Cache("TestmonialProductcard");
          Admin.openwebsite("TestmonialProductcard");
          Admin.verify_Padding_fronytend("TestmonialProductcard");
          Admin.Verify_frontend_Testimonial("Author one",1);
          Admin.Verify_frontend_Testimonial("Author Two",2);
         Admin.Verify_frontend_Testimonial("Author Three",3);
         Admin.verify_Padding_fronytend("Author one");
         Admin.verify_Padding_fronytend("Author Two");
         Admin.verify_Padding_fronytend("Author Three");
         
         Admin.clone_Testimonials();
         Admin.page_Cache("TestmonialProductcard");
         Admin.openwebsite("TestmonialProductcard");
         Admin.clone_testimonials_frontend("Author Three");
        Admin.deletepage("TestmonialProductcard");
          Admin.Clearfilter();

        } catch (Exception e) {



          Assert.fail(e.getMessage(), e);
        }
    }


  @AfterTest
    public void clearBrowser() {
      Common.closeAll();



  }



  @BeforeTest
    public void startTest() throws Exception {
        System.setProperty("configFile", "Admin\\config.properties");
        Login.signIn();


  }

}