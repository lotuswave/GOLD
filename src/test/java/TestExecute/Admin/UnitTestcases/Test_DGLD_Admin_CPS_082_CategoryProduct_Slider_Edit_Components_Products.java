package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CPS_082_CategoryProduct_Slider_Edit_Components_Products {

	
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void CategoryProduct_Slider_Verify_CategoryProduct_Slider_Components_Products() throws Exception {
    try {
    	
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();    
           Admin.dragndrop_Category_ProductSlider(); 
           Admin.editCategory_Productslider();  
           Admin.cick_products();
           Admin.Savecategorypage("CategoryProducts");            
           Admin.savecontent("CategoryProducts");
           Admin.openwebsite("promocontent");          
           Admin.website_category_verification();          
           Admin.Click_SKU("ProductcardTile");
           Admin.openwebsite("promocontent");  
           Admin.website_SKU_verification();
           Admin.click_condition("CategoryProducts");
           Admin.savecontent("CategoryProducts");
           Admin.openwebsite("promocontent");  
           Admin.website_condition_verification();    
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