package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_046_Reset_Password_Validate_search_Guest_Checkout_validate_Minicart_and_Login_from_shipping_Page_Express_Paypal {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile,"CreateAccount");
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu1 = new GoldOspreyUS_PRODHyvaHelper(datafile,"Minicart");
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu2 = new GoldOspreyUS_PRODHyvaHelper(datafile,"Checkout payments");
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu3 = new GoldOspreyUS_PRODHyvaHelper(datafile,"My AccountPage");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Reset_Password_Validate_search_Guest_Checkout_validate_Minicart_and_Login_from_shipping_Page_Express_Paypal() throws Exception {

		try {

				Osprey_ReEu.verifingHomePage();
				Osprey_ReEu.click_singinButton();
	        	Osprey_ReEu.Forgot_password("Account");
	        	Osprey_ReEu1.search_product("Product");     
	        	Osprey_ReEu1.addtocart("Product");
	        	Osprey_ReEu2.Accessories_Header("Accessories");
	        	Osprey_ReEu1.simple_addtocart("Simple product"); 
	        	Osprey_ReEu1.click_minicart();
	        	Osprey_ReEu1.clickontheproduct_and_image("Product");
	           	Osprey_ReEu1.minicart_freeshipping();
	        	Osprey_ReEu1.minicart_delete("Product");                                        
	        	Osprey_ReEu1.minicart_product_close();
	        	Osprey_ReEu1.minicart_validation("Product Qunatity"); 
	        	Osprey_ReEu2.minicart_Checkout();
	        	Osprey_ReEu2.addDeliveryAddress_Guestuser("Account");
	        	Osprey_ReEu2.selectshippingmethod("GroundShipping method");
	        	Osprey_ReEu2.Express_Paypal("PaypalDetails");
	        	Osprey_ReEu. paypal_close();
	        	Osprey_ReEu.click_singin_Shippingpage();
	        	Osprey_ReEu3.Signin_Checkoutpage("Account");
	        
	       
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