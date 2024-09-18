package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_035_OXO_guest_Magento_Retail_Order_Placement_PriceOverride_Lowervalue {

	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Retailer OrderPlacement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_RetailerOrder_PriceOverride_LowerValue() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.click_Sales();
			Admin.Click_Orders_Salesmenu();
			Admin.Click_CreatNewOrders();
			Admin.Select_ExistingUser_email("OpsreyEMEAWebsite");
			Admin.Select_Store("OpsreyEMEAWebsite");
			Admin.Add_product_SKU("OpsreyEMEAWebsite");
			Admin.update_customprice("OpsreyEMEAWebsite");
			Admin.shippingaddress("OpsreyEMEAWebsite");
			Admin.Select_Shipping_method("OpsreyEMEAWebsite");
			Admin.Update_StoreCredit_ExistingCustomer("OpsreyEMEAWebsite");
			Admin.Select_Storecredit_payment_method("OpsreyEMEAWebsite");
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
