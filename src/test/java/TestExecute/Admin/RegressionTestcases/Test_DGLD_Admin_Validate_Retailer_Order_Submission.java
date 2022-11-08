package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Validate_Retailer_Order_Submission {
	
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"MagentoOrderplacement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Catalog_product_update () throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails");
    	Admin.Customers();
    	Admin.Allcustomers();
    	Admin.SelectCustomer_Edit("RetailOrder");
    	Admin.Edit_Customer_StoreCredit("RetailOrder");
    	Admin.click_Sales();
    	Admin.Click_Orders_Salesmenu();
    	Admin.Click_CreatNewOrders(); 
    	Admin.Enter_email("RetailOrder");
    	Admin.Select_astore();
        Admin.Add_product_SKU("RetailOrder");
    	//Admin.shippingaddress("RetailOrder");
    	Admin.Select_Shipping_method();
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
