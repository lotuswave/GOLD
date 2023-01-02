package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_050_RMA_Order_Placement_for_Hydroflask {
	
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Retailer RMA");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void RMA_Order_Placement_for_Hydroflask () throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails");
    	Admin.Customers();
    	Admin.Allcustomers();
    	Admin.SelectCustomer_Edit("RetailOrder");
    	Admin.Customer_StoreCredit_Blanace("RetailOrder");
    	Admin.reorder_from_customers("RetailOrder");
    	Admin.Add_product_SKU("RetailOrder");
    	Admin.Address_registeruser("RetailOrder");
    	Admin.Shipping_method("RetailOrder");
    	Admin.Select_Storecredit_payment_method();
    	Admin.Submit_RetailOrder_Success();

    	
           
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
