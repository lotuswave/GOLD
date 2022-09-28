
package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CM_006_AlternatePath_Admin_Edits_a_Customer_Segment {
	 String datafile = "Admin//AdminTestData.xlsx";    
	 Adminhelper Admin = new Adminhelper(datafile,"DataSet");
     @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
     public void Admin_Edits_a_Customer_Segment() throws Exception {

         try {
         	Admin.Admin_signin("AccountDetails");
         	Admin.Customers();
         	Admin.Customer_segments();
//         	Admin.Add_Save_customer_segments("Customer Segment");
         	Admin.Edit_customer_segment("Customer Segment");
          

         }
         catch (Exception e) {

             Assert.fail(e.getMessage(), e);
         } 
     }
    
//     @AfterClass
//     public void Admin_deletes_a_Customer_Segment() {
//     	Admin.delete_customer_segment("Customer Segment");
//     }
     
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

