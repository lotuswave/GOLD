package  TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_VPB_006_Verify_the_Value_Prop_Banner_Components {

    String datafile = "Admin//GoldAdminTestData.xlsx";    
    GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Valueprop");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_the_Value_Prop_Banner_Components() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Promo_Content();
           Admin.dragndrop_valueprop_Banner();
           Admin.edit_valueprop_banner();
           Admin.editpromocontent_color("promocontent");
           Admin.edit_heading_mobile_valueprop_banner("promocontent");
           Admin.edit_valueprop_banner_one();
           Admin.Configure_padding_marins("Valuepropbanner");
           Admin.icon_image_one("promocontent");
           Admin.edit_valueprop_banner_Two(); 
           Admin.Configure_padding_marins("valuepropcard2");
           Admin.icon_image_two("promocontent");
           Admin.edit_valueprop_banner_Three();
           Admin.Configure_padding_marins("valuepropcard3");
           Admin.icon_image_galary("promocontent"); 
           Admin.edit_valueprop_banner();
           Admin.close_valueprop_page();
           Admin.edit_valueprop_banner_one();
           Admin.close_individual_page_one();
           Admin.edit_valueprop_banner_Two();
           Admin.close_individual_page_Two();
           Admin.edit_valueprop_banner_Three();
           Admin.close_individual_page_Three();
           Admin.savecontent("promocontent");
           Admin.page_Cache("FlushMagento");
           Admin.openwebsite("promocontent");
           Admin.valueprop_website();  
           Admin.verify_Padding_fronytend("Valuepropbanner");
           Admin.verify_Padding_fronytend("valuepropcard2");
           Admin.verify_Padding_fronytend("valuepropcard3");
           Admin.clone_valueprop_banner();  
           Admin.page_Cache("FlushMagento");
           Admin.vlaueprop_clone_frontend("promocontent"); 
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