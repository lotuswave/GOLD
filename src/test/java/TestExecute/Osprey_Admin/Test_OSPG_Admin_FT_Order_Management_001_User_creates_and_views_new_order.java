package TestExecute.Osprey_Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_FT_Order_Management_001_User_creates_and_views_new_order {
	
	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";

	OsperyAdminHelper Osprey_Admin = new OsperyAdminHelper(datafile, "Order_Management");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Admin_creates_and_Views_a_Specific_Order() throws Exception {
		try {
			
			
			Osprey_Admin.Admin_signin("AccountDetails");
			Osprey_Admin.click_sales();
			Osprey_Admin.click_Orders_Salesmenu();
			Osprey_Admin.Click_CreatNewOrders();
			Osprey_Admin.Click_CreatNewCustomer();
			Osprey_Admin.Select_astore();
			Osprey_Admin.Add_product_SKU("Details");
			Osprey_Admin.Guestuser_shippingaddress("Details");
			String Order=Osprey_Admin.Default_Payment_method("Details");
			Osprey_Admin.click_sales();
			Osprey_Admin.click_Orders_Salesmenu();
			Osprey_Admin.enter_orderNumber(Order);
//			Osprey_Admin.Click_View_order(Order);
			Osprey_Admin.Update_an_OrderBilling("BillingAddress");
			Osprey_Admin.Update_an_OrderShipping("ShippingAddress");
			Osprey_Admin.Click_Void();
			Osprey_Admin.Click_Invoice();
			Osprey_Admin.Invoice_Payment("AccountDetails");
			Osprey_Admin.Click_Ship();
			Osprey_Admin.Ship_Payment("AccountDetails");
			Osprey_Admin.Click_Creditmemo();
			Osprey_Admin.Creditmemo_create("AccountDetails");
			
			
			
		
			
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
		System.setProperty("configFile", "Osprey_Admin\\config.properties");
		Login.signIn();

		Login.signIn();

	}
	
	

}

