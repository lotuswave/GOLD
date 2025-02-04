package TestExecute.Hydroflask.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_040_Create_Account_Validation_Checkout_Simple_Configurable_Engraving_products_Shopping_CartPage_Discount_with_Afterpay_Klarna_Payments {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_Account() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_Createaccount();
			Hydro.createaccount_verfication("Invalid details");
            Hydro.create_account("New Account Details");
            Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.search_product("Product");      
			Hydro.addtocart("Product"); 
			Hydro.Bottles_headerlinks("Bottles & Drinkware");   
			Hydro.Text_Engraving("Engraving Product");
			Hydro.enraving_Checkout("Horizontal Text");
			Hydro.back_to_cart();
			Hydro.update_shoppingcart("Product Qunatity");
			Hydro.Edit_Engraving_to_Graphic("Engraving Product");
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.back_to_cart();
			Hydro.minicart_ordersummary_discount("Discount");
			Hydro.updateproductcolor_shoppingcart("Color Product");
			Hydro.click_minicart();
			Hydro.minicart_Checkout();
			Hydro.After_Pay_payment("Afterpay");
			Hydro.Kalrna_Payment("Klarna Visa Payment");
			Hydro.back_to_cart();
			Hydro.deleteProduct_shoppingcart();
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

	}

}
