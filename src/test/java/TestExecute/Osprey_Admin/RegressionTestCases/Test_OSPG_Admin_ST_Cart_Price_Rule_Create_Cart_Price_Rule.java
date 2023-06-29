package TestExecute.Osprey_Admin.RegressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_ST_Cart_Price_Rule_Create_Cart_Price_Rule {

	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";

	OsperyAdminHelper Osprey_Admin = new OsperyAdminHelper(datafile, "CartRulePrice");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Sceduled_categoryupdate() throws Exception {

		try {
			 
		   Osprey_Admin.Admin_signin("AccountDetails");
		   Osprey_Admin.Click_Marketing();
		   Osprey_Admin.select_cart_price_rule();
		   Osprey_Admin.Click_AddNewRule("AccountDetails");
		   Osprey_Admin.Rule_information("AccountDetails");
		   Osprey_Admin.open_Website("Address");
		   Osprey_Admin.search_product("Product");
		   Osprey_Admin.addtocart("Product");
		   Osprey_Admin.minicart_Checkout();
		   Osprey_Admin.addDeliveryAddress_Guestuser("Addressbook");
		   Osprey_Admin.clickSubmitbutton_Shippingpage();
		   Osprey_Admin.discountCode("Discount");
		   Osprey_Admin.updatePaymentAndSubmitOrder("PaymentDetails");
		   Osprey_Admin.Backto_magento_admin();
		   Osprey_Admin.delet_existing_Coupon("AccountDetails");

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

	}

}
