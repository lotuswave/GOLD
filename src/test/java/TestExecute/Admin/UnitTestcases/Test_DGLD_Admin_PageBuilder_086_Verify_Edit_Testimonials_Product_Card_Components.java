package  TestExecute.Admin.UnitTestcases;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;





import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;


public class Test_DGLD_Admin_PageBuilder_086_Verify_Edit_Testimonials_Product_Card_Components {


    String datafile = "Admin//AdminTestData.xlsx";
    Adminhelper Admin = new Adminhelper(datafile,"DataSet");


  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Admin_validateproductcard_configuration() throws Exception {


      try {
            Admin.Admin_signin("AccountDetails");
            Admin.click_content();
            Admin.pages();
            Admin.NewpageCTA();
            Admin.Contentpage();
            Admin.hot_elements(); 
            Admin.Testimonials_Product_Carousel();
            Admin.dragndrop_Testimonials_Product_Carousel();
            Admin.Edit_Testimonials_Product_Carousel();
            Admin.SKUvalue("TestmonialProductcard");
            Admin.Content_Dark("TestmonialProductcard");
            Admin.Editcardtile_color("TestmonialProductcard");
            Admin.CSSclassvalue("TestmonialProductcard");
            Admin.Configure_padding_marins("TestmonialProductcard");
            Admin.savecontent1("TestmonialProductcard");
            Admin.openwebsite("TestmonialProductcard");
            Admin.frontendView_Dark();
            Admin.Navigate_adminpage();
        //    Admin.edit_Testimonial_one();
            Admin.Edit_Testimonials_Product_Carousel();
            Admin.Content_Light();
            Admin.Editandsavepage();
            Admin.savecontent("TestmonialProductcard");
          Admin.shifttab();
         //  Admin.openwebsite("TestmonialProductcard");
            Admin.frontendView_Light();
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