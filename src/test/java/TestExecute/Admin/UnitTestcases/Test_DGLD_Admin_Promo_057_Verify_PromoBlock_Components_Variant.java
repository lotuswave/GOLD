package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_Promo_057_Verify_PromoBlock_Components_Variant {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void C() throws Exception {
    try {
    	
           Admin.Admin_signin("AccountDetails");
           Admin.click_content();
           Admin.pages();
           Admin.newpageCTA("promocontent");
           Admin.Contentpage();
           Admin.hot_elements();
           Admin.dragndrop_promocontentBlock();        
           Admin.editpromocontent();       
           Admin.edit_titlepromocontent_color("promocontent");
           Admin.editpromocontent_image();
           Admin.allbackground("promocontent"); 
           Admin.savecontent("promocontent"); 
           Admin.openwebsite("promocontent");                      
           Admin.clickAlternative();
           Admin.savecontent("promocontent");        
           Admin.verficationAlternative();
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
