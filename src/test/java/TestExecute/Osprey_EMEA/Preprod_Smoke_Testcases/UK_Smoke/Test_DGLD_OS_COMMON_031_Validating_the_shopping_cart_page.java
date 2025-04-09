package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.UK_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_031_Validating_the_shopping_cart_page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Checkout_RegisterUserCC_configurable_Simple () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.simple_addtocart("Simple product");  
        Osprey_ReEu.search_product("Product");     
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.click_minicart();
        Osprey_ReEu.minicart_viewcart();
        Osprey_ReEu.update_shoppingcart("Product Qunatity");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
