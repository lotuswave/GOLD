package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_023_Promotion_Cart_Rule_Prices {
	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "CartRulePrice");
	OspreyEMEA_HYVA Website_Emea = new OspreyEMEA_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Catalog_product_update() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.Click_Marketing();
			Admin.select_cart_price_rule();
			Admin.Click_AddNewRule("AccountDetails");
			Admin.Rule_information("AccountDetails");
			Admin.open_website("AccountDetails");
			
			Website_Emea.search_product("Product");
	        Website_Emea.addtocart("Product");
	        Website_Emea.minicart_Checkout();
	        Website_Emea.addDeliveryAddress_Guestuser("Account");
	        Website_Emea.selectshippingmethod("GroundShipping method");
	        Website_Emea.clickSubmitbutton_Shippingpage();
	        Website_Emea.discountCode("Discount");
	        Website_Emea.updatePaymentAndSubmitOrder("CCMastercard");
			
		/*	Admin.open_website("Address");
			Admin.search_product("Product");
			Admin.addtocart("Product");
			Admin.minicart_Checkout();
			Admin.addDeliveryAddress_Guestuser("Addressbook");
			Admin.clickSubmitbutton_Shippingpage();
			Admin.discountCode("Discount");
			Admin.updatePaymentAndSubmitOrder("PaymentDetails");*/
		/*	Admin.open_website("OXO");
			Admin.coffee_headerlinks("Coffee & Beverage");
			Admin.addtocart("addproduct");
			Admin.minicart_Checkout();
			Admin.addDeliveryAddress_Guestuser("Addressbook");
			Admin.clickSubmitbutton_Shippingpage();
			Admin.discountCode("Discount");
			Admin.updatePaymentAndSubmitOrder("PaymentDetails");
			Admin.Backto_magento_admin();
			Admin.delet_existing_Coupon("AccountDetails");
*/
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

		//Login.signIn();

	}

}
