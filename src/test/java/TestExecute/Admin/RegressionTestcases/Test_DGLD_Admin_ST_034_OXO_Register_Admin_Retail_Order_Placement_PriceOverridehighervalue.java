package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_034_OXO_Register_Admin_Retail_Order_Placement_PriceOverridehighervalue {

	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Retailer OrderPlacement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_RetailerOrder_PriceOverride_HigherValue() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.click_Sales();
			Admin.Click_Orders_Salesmenu();
			Admin.Click_CreatNewOrders();
			Admin.Select_ExistingUser_email("OXOWebsite");
			Admin.Select_Store("OXOWebsite");
			Admin.Add_product_SKU("OXOWebsite");
			Admin.update_customprice("HighPriceOverride");
			Admin.shippingaddress("OXOWebsite");
			Admin.Select_Shipping_method("OXOWebsite");
			Admin.Update_StoreCredit_ExistingCustomer("OXOWebsite");
			Admin.Select_Storecredit_payment_method();
			Admin.Submit_RetailOrder_Success();
			 

		} catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}
	
	
}
