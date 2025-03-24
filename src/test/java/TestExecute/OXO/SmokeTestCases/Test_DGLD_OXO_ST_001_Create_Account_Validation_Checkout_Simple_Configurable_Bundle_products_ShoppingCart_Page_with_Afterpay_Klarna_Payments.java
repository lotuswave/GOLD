package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_001_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_ShoppingCart_Page_with_Afterpay_Klarna_Payments {

	String datafile = "OXO//GoldOxoTestData.xlsx";
	GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_ShoppingCart_Page_with_Afterpay_Klarna_Payments() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_Createaccount();
			Oxo.createaccount_verfication("Invalid details");
			Oxo.create_account("New Account Details");
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.search_product("ConfigProduct");
			Oxo.Configurable_addtocart_pdp("ConfigProduct");
			Oxo.search_product("Bundle");
			Oxo.addtocart("Bundle");
			Oxo.minicart_viewcart();
			Oxo.update_shoppingcart("Product Qunatity");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.Shoppingcart_page();
			Oxo.minicart_ordersummary_discount("Discount");
			Oxo.updateproductcolor_shoppingcart("ConfigProduct");
			Oxo.minicart_Checkout();
			Oxo.After_Pay_payment("Afterpay");
			Oxo.Kalrna_Payment("Klarna Visa Payment");
			Oxo.Shoppingcart_page();
			//Oxo.deleteProduct_shoppingcart();
			

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
		System.setProperty("configFile", "oxo\\config.properties");
		Login.signIn();
		Oxo.acceptPrivacy();
	}

}
