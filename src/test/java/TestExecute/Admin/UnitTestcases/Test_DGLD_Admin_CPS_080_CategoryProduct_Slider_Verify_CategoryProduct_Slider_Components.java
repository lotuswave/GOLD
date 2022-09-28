package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CPS_080_CategoryProduct_Slider_Verify_CategoryProduct_Slider_Components {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void CategoryProduct_Slider_Verify_CategoryProduct_Slider_Components() throws Exception {
    try {
    	
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();    
           Admin.dragndrop_Category_ProductSlider(); 
           Admin.editCategory_Productslider();         
           Admin.savecategory_product("CategoryProducts");       
           Admin.savecontent("CategoryProducts");
           Admin.openwebsite("promocontent");  
           Admin.webiste_title();
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
    	
    	
          Login.signIn();

      }

    

}
