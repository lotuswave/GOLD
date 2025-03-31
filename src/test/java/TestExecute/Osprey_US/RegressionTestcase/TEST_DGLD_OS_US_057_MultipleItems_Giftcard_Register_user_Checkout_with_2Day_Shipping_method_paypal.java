package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_057_MultipleItems_Giftcard_Register_user_Checkout_with_2Day_Shipping_method_paypal {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_user_Checkout_with_2Day_Shipping_method_Multipleitems_PP_GC () throws Exception {

		try {
			    Osprey_ReEu.verifingHomePage();
			    Osprey_ReEu.click_singinButton();
			    Osprey_ReEu.Login_Account("Account");
			    Osprey_ReEu.Gift_cards("Osprey Gift Card");
	            Osprey_ReEu.Card_Value("Gift Details");
	            Osprey_ReEu.search_product("poco Product");
	        	Osprey_ReEu.addtocart("poco Product");
	        	Osprey_ReEu.Accessories_Header("Accessories");
	       		Osprey_ReEu.simple_addtocart("Simple product"); 
	        	Osprey_ReEu.minicart_Checkout();
	        	Osprey_ReEu.RegaddDeliveryAddress("Account");
	        	Osprey_ReEu.selectshippingmethod("Bestway method");
	        	Osprey_ReEu.payPal_Payment("PaypalDetails");
        
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
