package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CPS_009_CategoryProduct_Slider_Verify_Edit_CategoryProduct_Slider_Components_Content_Products {

	String datafile = "Admin//GoldAdminTestData.xlsx";    
    GoldAdminHelper Admin = new GoldAdminHelper(datafile,"ProductSlider");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void CategoryProduct_Slider_Verify_CategoryProduct_Slider_Components_Content() throws Exception {
    try {
	
	
	Admin.Admin_signin("AccountDetails");
    Admin.click_content();
    Admin.pages();
    Admin.newpageCTA("CategoryProducts");
    Admin.Contentpage();
    Admin.hot_elements();    
    Admin.dragndrop_Category_ProductSlider(); 
    Admin.editCategory_Productslider(); 
    Admin.category_productslider("CategoryProducts");
    Admin.editcategorypage("Categorydetails");
    Admin.savecontent("CategoryProducts");	
	Admin.Contentpage();
    Admin.hot_elements(); 
    Admin.editCategory_Productslider();
    Admin.Configure_padding_marins("CategoryProducts");
    Admin.Editandsavepage();
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts"); 
    Admin.website_verification_categroeyslider();
    Admin.verify_Padding_fronytend("CategoryProducts");
    Admin.click_product("Categorydetails");
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts");;
    Admin.Websiteverification_product();
    Admin.click_categoty("Categorydetails");
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts"); 
    Admin.Websiteverification_category();
    Admin.Click_Page("Categorydetails");
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts");            
    Admin.Websiteverification_page();
    Admin.produts();
    Admin.cick_products();
    Admin.Savecategorypage("CategoryProducts");            
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts");          
    Admin.website_category_verification();
    Admin.verify_Padding_fronytend("CategoryProducts");
    Admin.Click_SKU("ProductcardTile");
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts");  
    Admin.website_SKU_verification();
    Admin.verify_Padding_fronytend("CategoryProducts");
    Admin.click_condition("CategoryProducts");
    Admin.savecontent("CategoryProducts");
    Admin.openwebsite("CategoryProducts");  
    Admin.website_condition_verification();
    Admin.verify_Padding_fronytend("CategoryProducts");
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
