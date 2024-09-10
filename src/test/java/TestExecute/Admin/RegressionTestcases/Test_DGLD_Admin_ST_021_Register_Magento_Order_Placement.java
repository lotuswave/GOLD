package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_021_Register_Magento_Order_Placement {
	
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Mogento,orderplacement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	
    	  Admin.Admin_signin("AccountDetails");
          Admin.click_Sales();
          Admin.Click_Orders_Salesmenu();
          Admin.Click_CreatNewOrders();
          Admin.Select_ExistingUser_email("HFWebsite");
          Admin.Select_Store("HFWebsite");
          Admin.Add_product_SKU("Details");
          Admin.shippingaddress("Details");
          Admin.Select_Shipping_method("HFWebsite");
          Admin.Default_Payment_method("Details");
    	
           
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





