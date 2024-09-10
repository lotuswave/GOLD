package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_032_Register_HF_CreateNewOrder_GroupedProduct {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Mogento,orderplacement");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void ExistingUser_CreateNewOrder_GroupedProduct() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.click_Sales();
			Admin.Click_Orders_Salesmenu();
			Admin.Click_CreatNewOrders();
			Admin.Select_ExistingUser_email("HFWebsite");
			Admin.Select_Store("HFWebsite");
			Admin.AddProduct_By_SKU_Group_Product("HFGroupproduct");
			Admin.AddProduct_By_SKU_Configure_child_Products("HFGroupproduct");
			Admin.shippingaddress("Details");
			Admin.Select_Shipping_method("HFWebsite");
			Admin.Default_Payment_method("Details");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
//		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

		Login.signIn();

	}

}
