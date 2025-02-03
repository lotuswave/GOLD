package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarus_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_US_ST_040_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_ShoppingCart_Page_with_Afterpay_Klarna_Payments {
	
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarus_PRODHyvaHelper Drybar = new GoldDrybarus_PRODHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_Account_Validation_Checkout_Simple_Configurable_Bundle_products_ShoppingCart_Page_with_Afterpay_Klarna_Payments () throws Exception {

		try {
			Drybar.Verify_Homepage();
			Drybar.ClickCreateAccount();
			Drybar.createaccount_verfication("Invalid details");
			Drybar.create_account("NewAccountDetails");
			Drybar.search_product("Bundle Product");
			Drybar.Addtocart_Bundle("Bundle Product");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
		    Drybar.Remove_Product("Configurable Product");
	        Drybar.update_shoppingcart("Product Qunatity");
	        Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.Shoppingcart_page();
//			Drybar.minicart_ordersummary_discount("Discount"); As discount code is not available this method has been commented
			Drybar.minicart_Checkout();
			Drybar.Kalrna_Payment("Klarna Visa Payment");
	        Drybar.After_Pay_payment("Afterpay");
	        

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
}
