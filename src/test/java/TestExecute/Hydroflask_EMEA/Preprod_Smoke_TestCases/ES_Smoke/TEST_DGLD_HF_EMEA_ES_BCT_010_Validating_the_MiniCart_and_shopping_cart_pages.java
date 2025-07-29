package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.ES_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_ES_BCT_010_Validating_the_MiniCart_and_shopping_cart_pages {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_MiniCart_and_shopping_cart_page() throws Exception {

		try {
	  /*	Minicart Validation:	*/
			Hydro.verifingHomePage();    
			Hydro.search_product("Configurable Product"); 
			Hydro.Configurable_addtocart_pdp("Price Product");
			Hydro.search_product("Product");      
			Hydro.addtocart("Product"); 
			//Hydro.clickontheproduct_and_image("Product");
			Hydro.minicart_freeshipping();
			Hydro.minicart_delete("Price Product");
			Hydro.minicart_validation("Product Qunatity");
			
	  /*    Shoppingcart Validation:    */
			Hydro.search_product("Configurable Product"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.click_minicart();
			Hydro.minicart_viewcart();
			Hydro.Remove_Product("Product");
			Hydro.update_shoppingcart("Product Qunatity");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("es_Address");
            Hydro.selectshippingaddress("GroundShipping method");
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
		String url="https://mcloud-na-preprod.hydroflask.com/es";
		System.setProperty("url", url);
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}