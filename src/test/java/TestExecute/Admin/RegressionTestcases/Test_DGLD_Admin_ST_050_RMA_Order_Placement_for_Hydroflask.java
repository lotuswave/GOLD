package TestExecute.Admin.RegressionTestcases;

import java.util.HashMap;

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
   	    HashMap<String,String> data=Admin.productprice_and_Quantity();
   	    System.out.println(data);
    	Admin.Address_registeruser("RetailOrder");
    	Admin.Shipping_method("RetailOrder");
    	Admin.Select_Storecredit_payment_method("RetailOrder");;
    	String ordernumber=Admin.Submit_RetailOrder_Success();
//    	String Address=Admin.Address_and_Sku_Validation("RetailOrder");
    	Admin.Login_Kustomerwebsite("AccountDetails");
    	Admin.search_ordernumber(ordernumber);
       
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
