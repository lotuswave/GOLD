package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_PROMO_004_Validate_PromoMedia_Block_Components {

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
	           Admin.dragndrop_promoBlock();
	           Admin.edit_promoBlock_one(); 
	           Admin.editpromocontent_color("promocontent");
	           Admin.editpromocontent_image();	         
	           Admin.allbackground("promocontent");	  	         
	           Admin.edit_promoBlock_two();
	           Admin.editpromocontent_color("promocontent");
	           Admin.editpromocontent_video("promocontent");
	           Admin.editpromocontent_fallback_image();
	           Admin.savecontent("promocontent");
	           Admin.openwebsite("promocontent");	          
	           Admin.video_promo_component();	
	           Admin.clone_page_promo_block("promocontent");
	           Admin.clone_page_valueprop_forntend();
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
