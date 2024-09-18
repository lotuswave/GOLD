package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_025_Register_Admin_Retail_Order_Placement_with_Priceoverride_highvalue {
	
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Retailer OrderPlacement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Register_Admin_Retail_Order_Placement_with_HighPriceoverride() throws Exception {
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
        String updatedprice = Admin.update_customprice_withhighprice("HighPriceOverride");
    	//Admin.shippingaddress("RetailOrder");
    	Admin.Select_Shipping_method("RetailOrder");
    	Admin.Select_Storecredit_payment_method("RetailOrder");
    	Admin.Submit_RetailOrder_Success();
    	Admin.validate_updated_Highpricevalue_in_Order(updatedprice);
    	
           
    	
           
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
