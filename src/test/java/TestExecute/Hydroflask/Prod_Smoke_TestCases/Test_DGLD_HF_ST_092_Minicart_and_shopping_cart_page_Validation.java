package TestExecute.Hydroflask.Prod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_092_Minicart_and_shopping_cart_page_Validation {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Minicart_and_shopping_cart_Functionality() throws Exception {

		try {
			Hydro.verifingHomePage();
/*
 * Minicart Validation:			
 */
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.click_minicart();
			Hydro.clickontheproduct_and_image("Product");
			Hydro.minicart_freeshipping();
			Hydro.minicart_delete("Product");
			Hydro.minicart_product_close();
/*
 * Shopping cart validation			
 */
			Hydro.minicart_validation("Product Qunatity");
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.click_minicart();
			Hydro.minicart_viewcart();
			Hydro.Remove_Product("Product");
			Hydro.update_shoppingcart("Product Qunatity");
			Hydro.click_minicart();
			Hydro.minicart_Checkout();
			Hydro.Shoppingcart_page();
			Hydro.minicart_ordersummary_discount("Discount");
			
			
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
		System.setProperty("configFile", "Hydroflask\\prodconfig.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

	}

}
