package TestExecute.Admin.RegressionTestcases;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;
 
public class Test_DGLD_Admin_ST_011_Configure_and_validate_Page_Content {
 
 
	String datafile = "Admin//GoldAdminTestData.xlsx";
 
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Content");
 
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Admin_validate() throws Exception {
 
	try {
		Admin.Admin_signin("AccountDetails");
		Admin.click_content();
	    Admin.pages();
	    Admin.newpageCTA("Content");
	    Admin.Contentpage();
		Admin.hot_elements();

		//**** HERO BANNER Block ****//
		Admin.PLPCMS_content();
		Admin.dragndrop_heroBanner();
		Admin.edit_Herobanner();
		Admin.edit_Herobanner_ContentSection("Content");
		Admin.click_website_categoty("Content");
		Admin.herobanner_images("Content");
        Admin.edit_HeroBanner_ContentAlignment("Content");
        Admin.edit_Hero_Banner_content_color("Content");
        Admin.allbackground("Content");
        Admin.savecontent("Content");
        Admin.page_Cache("FlushMagento");
        Admin.openwebsite("Content");
        Admin.website_verification_Herobanner();
        Admin.website_image_verification_herobanner("Content");
        Admin.Websiteverification_hero_product();  
        
       // ***** CLP HERO BANNER Block ******//
        Admin.Search_previous_page_Magento("Content");
		Admin.Contentpage();
		Admin.hot_elements();
		Admin.PLPCMS_content();
		Admin.dragndrop_CLP_Hero_Content();
		Admin.edit_CLP_block_content("CLPHerobanner");
		Admin.CMS_ImageElement("CLPHerobanner");
		Admin.Editandsavepage();
		Admin.savecontent("CLPHerobanner");
		Admin.page_Cache("FlushMagento");
		Admin.openwebsite("CLPHerobanner");
		Admin.ClosADD();
		Admin.AcceptAll();
		Admin.validate_content_alignment("left");
		Admin.Validate_CLP_Content_Frontend("CLPHerobanner");  
		
		//****  CATGEORY/PRODUCT SLIDER Block ****//
        Admin.Search_previous_page_Magento("Content");
        Admin.Contentpage();
        Admin.hot_elements();    
        Admin.dragndrop_Category_ProductSlider();
        Admin.editCategory_Productslider(); 
        Admin.category_productslider("Categorydetails");
        Admin.editcategorypage("Categorydetails");
        Admin.savecontent("CategoryProducts");	
        Admin.page_Cache("FlushMagento");
        Admin.openwebsite("CategoryProducts"); 
        Admin.website_verification_categroeyslider();   

	  //****  CARD TILES Block ****//
	    Admin.Search_previous_page_Magento("Content");
	    Admin.Contentpage();
	    Admin.hot_elements();
	    Admin.Cardtile_content();
		Admin.dragndrop_Cardtile_Content();
		Admin.edit_block_content("ProductcardTile");
		Admin.CardImage("ProductcardTile");
		Admin.featuredcardconfiguration_image("ProductcardTile");
		Admin.category_cards("ProductcardTile");
		Admin.Editandsavepage();
		Admin.savecontent("ProductcardTile");
		Admin.page_Cache("FlushMagento");
		Admin.openwebsite("ProductcardTile");
		Admin.verifycardtileimage_frontend("ProductcardTile");  

		 //****  CATGEORY/PRODUCT SLIDER Block ****//
        Admin.Search_previous_page_Magento("Content");
	    Admin.Contentpage();
	    Admin.hot_elements();    
	    Admin.dragndrop_Category_ProductSlider(); 
	    Admin.editCategory_Productslider2();
	    Admin.category_productslider("Content");
	    Admin.Savecategorypage("CategoryProducts");
	    Admin.Click_SKU("Categorydetails");
	    Admin.savecontent("Content");
	    Admin.page_Cache("FlushMagento");
	    Admin.openwebsite("Content");  
	    Admin.website_SKU_verification();

	    Admin.deletepage("Content");
        Admin.Clearfilter();
 
	} catch (Exception e) {
 
		Assert.fail(e.getMessage(), e);
	}
}
 
@AfterTest
public void clearBrowser() {
	//Common.closeAll();
 
}
 
@BeforeTest
public void startTest() throws Exception {
	System.setProperty("configFile", "Admin\\config.properties");
	Login.signIn();
 
}
 
	
}