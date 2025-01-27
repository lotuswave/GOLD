package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_040_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_Shopping_CartPage_Discount_with_Afterpay_Klarna_Payments {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile, "Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_Shopping_CartPage_Discount_with_Afterpay_Klarna_Payments() throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.click_Createaccount();
			Osprey_ReEu.createaccount_verfication("Invalid Email");
			Osprey_ReEu.create_account("Create Account");
		    Osprey_ReEu.Accessories_Header("Accessories");
		    Osprey_ReEu.simple_addtocart("Simple product");  
		    Osprey_ReEu.search_product("Product");     
		    Osprey_ReEu.addtocart("Product");
		    Osprey_ReEu.click_minicart();
		    Osprey_ReEu.minicart_viewcart();
		    Osprey_ReEu.Remove_Product("Product");
		    Osprey_ReEu.update_shoppingcart("Product Qunatity");
		    Osprey_ReEu.minicart_Checkout();
		    Osprey_ReEu.RegaddDeliveryAddress("Account");
		    Osprey_ReEu.Shoppingcart_page();
		    Osprey_ReEu.minicart_ordersummary_discount("Discount");
		    Osprey_ReEu.minicart_Checkout();
		    Osprey_ReEu.Kalrna_Payment("Klarna Visa Payment");
		    Osprey_ReEu.After_Pay_payment("Afterpay");
		    Osprey_ReEu.Shoppingcart_page();
		    Osprey_ReEu.deleteProduct_shoppingcart();
	        

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
		System.setProperty("configFile", "Osprey_US\\config.properties");
		Login.signIn();

	}

}
