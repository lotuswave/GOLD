package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_PromoBlock_005_Verify_the_Promo_Block_Components {

	
	 String datafile = "Admin//GoldAdminTestData.xlsx";    
	 GoldAdminHelper Admin = new GoldAdminHelper(datafile,"PromoContent");
	    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	    public void Verify_the_Promo_Block_Components() throws Exception {
	    try {
	    	   
	    	  Admin.Admin_signin("AccountDetails");
	          Admin.click_content();
	          Admin.pages();
	          Admin.newpageCTA("promocontent");
	          Admin.Contentpage();
	          Admin.hot_elements();
	          Admin.dragndrop_promocontentBlock();
	          Admin.editpromocontent();
	          Admin.edit_promoContentProduct_ContentSection("EditContentSection");
	          Admin.editpromocontent_color("promocontent");
	          Admin.editpromocontent_image();	         
	          Admin.Click_Promo_Product("Categorydetails");
	          Admin.savecontent("promocontent");
	          Admin.openwebsite("promocontent"); 
	          Admin.Websiteverification_hero_product();
	          Admin.Contentpage();
	          Admin.hot_elements();
	          Admin.editpromocontent();
	          Admin.Configure_padding_marins("Valuepropbanner");
	          Admin.Editandsavepage();
	          Admin.savecontent("promocontent");
	          Admin.page_Cache();
	          Admin.openwebsite("promocontent");	           
	          Admin.verify_Padding_fronytend("Valuepropbanner");   		      
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
	
