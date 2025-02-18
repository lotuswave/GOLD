package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_ST_CM_002_Create_Edit_Delete_Customer_Segment {
	
	 String datafile = "Admin//AdminTestData.xlsx";    
	 Adminhelper Admin = new Adminhelper(datafile,"customer");
        @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
        public void Admin_Create_Edit_Delete_Customer_Segment() throws Exception {

            try {
            	Admin.Admin_signin("AccountDetails");
            	Admin.Customers();
            	Admin.Customer_segments();
            	Admin.Add_Save_customer_segments("Customer Segment");
            	Admin.Edit_customer_segment("Customer Segment");
            	Admin.Delete_customer_segment("Customer Segment");
            	
            	
             
            }
            catch (Exception e) {

                Assert.fail(e.getMessage(), e);
            } 
        }

		
//		  @AfterClass
//		  
//		  public void Admin_Edits_a_Customer_Segment() {
//		  Admin.edit_customer_segment("Customer Segment"); }
//		  
//		  @AfterClass public void Admin_deletes_a_Customer_Segment() {
//		  Admin.delete_customer_segment("Customer Segment"); }
//		 
        

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



