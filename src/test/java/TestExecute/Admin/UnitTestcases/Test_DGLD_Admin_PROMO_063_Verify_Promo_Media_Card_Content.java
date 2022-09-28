package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_PROMO_063_Verify_Promo_Media_Card_Content {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Configure_and_validate_Promo_Media_Card_Content() throws Exception {
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
           Admin.CTA_content("CTA Edit");
           Admin.edit_promoBlock_two();
           Admin.editpromocontent_color("promocontent");
           Admin.editpromocontent_image();
           Admin.CTA_product_content("CTA Edit");
           Admin.savecontent("promocontent");
           Admin.openwebsite("promocontent");
           Admin.website_button_verification_promoblock("CTA Edit");
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
// @Parameters("URL")
    public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
         Login.signIn();
//          Login.openwebsite(URL);

      }

    

}