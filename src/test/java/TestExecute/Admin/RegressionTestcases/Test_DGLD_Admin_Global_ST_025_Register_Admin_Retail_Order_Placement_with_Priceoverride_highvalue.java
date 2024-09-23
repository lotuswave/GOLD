package TestExecute.Admin.RegressionTestcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Global_ST_025_Register_Admin_Retail_Order_Placement_with_Priceoverride_highvalue {
	
	String datafile = "Admin\\GoldAdminTestData.xlsx";    
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"Retailer OrderPlacement");
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    public void Register_Admin_Retail_Order_Placement_with_HighPriceoverride() throws Exception {
    try {
    	
    	Admin.Admin_signin("AccountDetails");
    	Admin.Customers();
    	Admin.Allcustomers();
    	Admin.SelectCustomer_Edit("DRYWebsite");
    	Admin.Edit_Customer_StoreCredit("DRYWebsite");
    	Admin.click_Sales();
    	Admin.Click_Orders_Salesmenu();
    	Admin.Click_CreatNewOrders(); 
    	Admin.Enter_email("DRYWebsite");
    	Admin.Select_store("DRYWebsite");
        Admin.Add_product_SKU("DRYWebsite");
        HashMap<String, String> updatedprice = Admin.update_customprice_withhighprice("DRYWebsite");
        System.out.println(updatedprice);
    	//Admin.shippingaddress("RetailOrder");
    	Admin.Select_Shipping_method("DRYWebsite");
    	Admin.Select_Storecredit_payment_method("DRYWebsite");
    	Admin.Submit_RetailOrder_Success();
    	Admin.validate_updated_Highpricevalue_in_Order(updatedprice);
    	
           
    	//DRYWebsite OpsreyUSWebsite HYFWebsite OXOWebsite OpsreyEMEAWebsite change website name According to your testing. 
           
        }
        catch (Exception e) {

            Assert.fail(e.getMessage(), e);
        } 
    }



    @AfterTest
    public void clearBrowser()
    {
    // Common.closeAll();

    }


    @BeforeTest
      public void startTest() throws Exception {
    	System.setProperty("configFile", "Admin\\config.properties");
        Login.signIn();
    	  	
          


      }
}
