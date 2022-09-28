package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_HB_078_081_Verify_Hero_Banner_Components_edit_content_Backgroundimage {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Configure_and_validate_Hero_Banner_Components() throws Exception {
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
           Admin.edit_HeroBanner_ContentAlignment("Herobanner");
           Admin.edit_Hero_Banner_content_color("Herobanner");
           Admin.editpromocontent_image();
           Admin.allbackground("Herobanner");
           Admin.savecontent("Herobanner");
           Admin.openwebsite("Herobanner");
           Admin.website_verification_Herobanner();
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
        Common.closeAll();

    }


    @BeforeTest

    public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
          Login.signIn();
        

      }

    

}