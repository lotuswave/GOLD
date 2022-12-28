package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_HB_010_Verify_Hero_Banner_Components_edit_content_Backgroundimage {

	
	
	String datafile = "Admin//GoldAdminTestData.xlsx";   
	
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Herobanner");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Hero_Banner_Components_edit_content_Backgroundimage() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("Herobanner");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.dragndrop_heroBanner();
           Admin.edit_Herobanner();
           Admin.edit_Herobanner_ContentSection("Herobanner");
           Admin.herobanner_image();
           Admin.edit_HeroBanner_ContentAlignment("Herobanner");
           Admin.edit_Hero_Banner_content_color("Herobanner");
           Admin.editherobanner_Background_image();
           Admin.allbackground("Herobanner");
           Admin.savecontent("Herobanner");
           
           Admin.page_Cache("FlushMagento");
           Admin.openwebsite("Herobanner");
          // Admin.ClosADD();
           Admin.AcceptAll();
           Admin.website_verification_Herobanner();
           Admin.website_image_verification_herobanner();
           Admin.editherobanner_video("Herobanner");
           Admin.savecontent("Herobanner");
           Admin.page_Cache("FlushMagento");
           Admin.openwebsite("Herobanner");
           Admin.website_verification_video_Herobanner();
          Admin.click_hero_product("Herobanner");
          Admin.savecontent("Herobanner");
           Admin.page_Cache("FlushMagento");
           Admin.openwebsite("Herobanner");
           Admin.verify_Padding_fronytend("Herobanner");
           Admin.Websiteverification_hero_product();
           Admin.clone_Hero_banner();
           
           Admin.page_Cache("FlushMagento");
           Admin.openwebsite("Herobanner");
           Admin.herobanner_clone_frontend("Herobanner");
              Admin.deletepage("Herobanner");
           Admin.Clearfilter();
           

        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e);
        } 
    }


    @AfterTest
    public void clearBrowser()
    {
      // Common.closeAll();

    }


    @BeforeTest

    public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
          Login.signIn();
        

      }

    
}

