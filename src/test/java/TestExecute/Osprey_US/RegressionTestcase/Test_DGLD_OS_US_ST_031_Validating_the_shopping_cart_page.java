package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST_031_Validating_the_shopping_cart_page {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_shopping_cart_page () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
	        Osprey_ReEu.Accessories_Header("Accessories");
	        Osprey_ReEu.simple_addtocart("Simple product");  
	        Osprey_ReEu.search_product("Product");     
	        Osprey_ReEu.addtocart("Product");
	        Osprey_ReEu.click_minicart();
	        Osprey_ReEu.minicart_viewcart();
	        Osprey_ReEu.Remove_Product("Product");
	        Osprey_ReEu.update_shoppingcart("Product Qunatity");
	        Osprey_ReEu.minicart_Checkout();
	        Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
	        Osprey_ReEu.Shoppingcart_page();
	        Osprey_ReEu.minicart_ordersummary_discount("Discount");
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
