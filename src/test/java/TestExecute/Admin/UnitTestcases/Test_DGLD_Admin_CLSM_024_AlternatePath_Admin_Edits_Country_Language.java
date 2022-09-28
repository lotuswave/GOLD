package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CLSM_024_AlternatePath_Admin_Edits_Country_Language {
	String datafile = "Admin//AdminTestData.xlsx";    
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Admin_Edits_Country_Language() throws Exception {

        try {
        	
        	Admin.Admin_signin("AccountDetails");
        	Admin.click_content();
        	Admin.click_Country_Language_Selector_Management();
        	Admin.views_List_of_Countries_languages();
//        	Admin.add_New_Countries_languages("Address");
        	Admin.edit_Country_Languages("Address");
//        	Admin.delete_Country_Selector("Address");
        	
      	
        	
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

