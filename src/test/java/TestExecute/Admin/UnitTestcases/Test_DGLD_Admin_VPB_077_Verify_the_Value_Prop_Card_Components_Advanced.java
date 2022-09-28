package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_VPB_077_Verify_the_Value_Prop_Card_Components_Advanced {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Verify_Value_Prop_Banner_Close_functionality() throws Exception {
    try {
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.Promo_Content();
           Admin.dragndrop_valueprop_Banner();
           Admin.edit_valueprop_banner_one();
           Admin. edit_productcard_image("Valuepropbanner");
           Admin.Configure_padding_marins("Valuepropbanner");
           Admin.edit_valueprop_banner_Two();
           Admin. edit_productcard_image("Valuepropbanner");
           Admin.Configure_padding_marins("valuepropcard2");
           Admin.edit_valueprop_banner_Three();
           Admin. edit_productcard_image("Valuepropbanner");
           Admin.Configure_padding_marins("valuepropcard3");
           Admin.savecontent("Valuepropbanner");
           Admin.openwebsite("Valuepropbanner");
           Admin.verify_Padding_fronytend("Valuepropbanner");
           Admin.verify_Padding_fronytend("valuepropcard2");
           Admin.verify_Padding_fronytend("valuepropcard3");
           Admin.openwebsite("valuepropcard2");
           Admin.verify_Padding_fronytend("Valuepropbanner");
           Admin.verify_Padding_fronytend("valuepropcard2");
           Admin.verify_Padding_fronytend("valuepropcard3");
          
   
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
