package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class  Test_DGLD_Admin_ST_024_Verifying_Advance_Pricing {

	
		String datafile = "Admin\\GoldAdminTestData.xlsx";    
		GoldAdminHelper Admin = new GoldAdminHelper(datafile,"CatalogPricerule");
	    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	    public void Catalog_product_update () throws Exception {
	    try {
	    	
	    	
	    	Admin.Admin_signin("AccountDetails");
	           Admin.Click_Catalog();
	           Admin.Click_Products_Catalogmenu();
	           Admin.Search_products("AccountDetails");
	           Admin.Click_SearchProduct(); 
	         Admin.Click_Edit();
	         Admin.Quantityincrease("Quantity");
	         Admin.stockstatus("Stockstatus");
	           
	          Admin.QAtest_Advanced_Pricing("AdvancedPricing");
	        
	          Admin.open_website("Address");
	           Admin.Homepage_searchproduct("searchproduct");
	           Admin.click_product();
	          
	         Admin.Backto_magento_admin();
	                 Admin.QAtestproduct_Advanced_Pricing("QATestProduct");
	                  //Admin.open_website("Address");
	         
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
	    	  	
	          //Login.signIn();


	      }
	}


