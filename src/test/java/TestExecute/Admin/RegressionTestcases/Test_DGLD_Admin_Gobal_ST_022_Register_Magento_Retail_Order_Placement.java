package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Gobal_ST_022_Register_Magento_Retail_Order_Placement {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Retailer OrderPlacement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Catalog_product_update() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.click_Sales();
			Admin.Click_Orders_Salesmenu();
			Admin.Click_CreatNewOrders();
			Admin.Select_ExistingUser_email("HYFWebsite");       //HYFWebsite,OXOWebsite,OpsreyUSWebsite,DRYWebsite
			Admin.Select_Store("HYFWebsite");
			Admin.Add_product_SKU("HYFWebsite");
			Admin.shippingaddress("HYFWebsite");
			Admin.Select_Shipping_method("HYFWebsite");
			Admin.Update_StoreCredit_ExistingCustomer("HYFWebsite");
			Admin.Select_Storecredit_payment_method("HYFWebsite");
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
