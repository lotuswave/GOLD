package TestExecute.Hydroflask.regressionTestcase;

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
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.back_to_cart();
			Hydro.minicart_ordersummary_discount("Discount");
			Hydro.updateproductcolor_shoppingcart("Color Product");
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