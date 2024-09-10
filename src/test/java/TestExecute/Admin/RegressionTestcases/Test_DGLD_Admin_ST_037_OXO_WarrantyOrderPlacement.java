package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_037_OXO_WarrantyOrderPlacement {
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Retailer OrderPlacement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void CreatNewWarentyOrder () throws Exception {
    try {
    	
    	
    	 Admin.Admin_signin("AccountDetails");
         Admin.click_Sales();
         Admin.Click_Orders_Salesmenu();
         Admin.Click_CreatNewOrders();
         Admin.Select_ExistingUser_email("OXOWebsite"); 
         Admin.Select_Store("OXOWebsite");
         Admin.Add_simpleproduct_SKU("OXOProducts");
         Admin.Add_configurableproduct_SKU("OXOProducts");
         Admin.update_customprice("CustomPrice");
         Admin.shippingaddress("OXOWebsite");
         Admin.Select_Shipping_method("OXOWebsite");
	     Admin.Submit_Replacement_Success();
           
        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e);
        } 
    }



    @AfterTest
    public void clearBrowser()
    {
 //    Common.closeAll();

    }


    @BeforeTest
      public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
        Login.signIn();
    	  	
          Login.signIn();


      }

    


}
