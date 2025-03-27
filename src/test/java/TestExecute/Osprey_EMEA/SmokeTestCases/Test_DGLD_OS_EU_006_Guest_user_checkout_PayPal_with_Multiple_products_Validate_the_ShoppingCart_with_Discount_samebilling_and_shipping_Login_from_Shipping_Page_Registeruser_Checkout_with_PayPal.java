package TestExecute.Osprey_EMEA.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_PRODHYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_006_Guest_user_checkout_PayPal_with_Multiple_products_Validate_the_ShoppingCart_with_Discount_samebilling_and_shipping_Login_from_Shipping_Page_Registeruser_Checkout_with_PayPal {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_PRODHYVA Osprey_ReEu = new OspreyEMEA_PRODHYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_user_checkout_PayPal_with_Multiple_products_Validate_the_ShoppingCart_with_Discount_samebilling_and_shipping_Login_from_Shipping_Page_Registeruser_Checkout_with_PayPal () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
       // Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.search_product("Simple product");
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
        Osprey_ReEu.minicart_Checkout();
//        Osprey_ReEu.payPal_Payment("PaypalDetails");
//        Osprey_ReEu.paypal_close();
//        Osprey_ReEu.Signin_Checkoutpage("Account");
        Osprey_ReEu.payPal_Payment("PaypalDetails");
        Osprey_ReEu.paypal_close();
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
