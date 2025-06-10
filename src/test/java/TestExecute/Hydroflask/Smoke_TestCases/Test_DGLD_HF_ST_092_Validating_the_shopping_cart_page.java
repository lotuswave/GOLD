package TestExecute.Hydroflask.Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_092_Validating_the_shopping_cart_page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_shopping_cart_page() throws Exception {

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
						Hydro.minicart_validation("Product Qunatity");
			/*
			 * Shopping cart validation			
			 */
						
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}