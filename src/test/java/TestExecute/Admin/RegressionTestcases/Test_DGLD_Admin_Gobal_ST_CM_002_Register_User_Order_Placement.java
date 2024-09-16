package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Gobal_ST_CM_002_Register_User_Order_Placement {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Mogento,orderplacement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Catalog_product_update() throws Exception {
		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Customers();
			Admin.Allcustomers();
			Admin.SelectCustomer_Edit("OSP_Store");
			Admin.Click_CreatNewOrders();
			Admin.Select_store("OSP_Store");
			Admin.Add_product_SKU("OSP_Store");
			Admin.Guestuser_shippingaddress("Details");
			Admin.Select_Shipping_method("OSP_Store");
			Admin.Default_Payment_method("Details");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	// OXO_Store HF_Store DBR_Store OSP_Store  OSP-EMEA_Store change as per your store Emea Details when you execute the emea website

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
