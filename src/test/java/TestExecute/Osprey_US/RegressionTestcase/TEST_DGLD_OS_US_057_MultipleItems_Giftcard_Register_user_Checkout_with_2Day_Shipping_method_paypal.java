package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_057_MultipleItems_Giftcard_Register_user_Checkout_with_2Day_Shipping_method_paypal {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_user_Checkout_with_2Day_Shipping_method_Multipleitems_PP_GC () throws Exception {

		try {
			    Osprey_ReEu.verifingHomePage();
			    Osprey_ReEu.click_singinButton();
			    Osprey_ReEu.Login_Account("Account");
			    Osprey_ReEu.search_product("Gift Details1");
			    Osprey_ReEu.Gift_cards("OS_Giftcard");
	            Osprey_ReEu.Card_Value("Price");
	            Osprey_ReEu.search_product("poco Product");
	        	Osprey_ReEu.addtocart("poco Product");
	       	 	Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
	       		Osprey_ReEu.simple_addtocart("Simple product"); 
	        	Osprey_ReEu.minicart_Checkout();
	        	Osprey_ReEu.RegaddDeliveryAddress("Account");
	        	Osprey_ReEu.selectshippingmethod("Bestway method");
	        	Osprey_ReEu.clickSubmitbutton_Shippingpage();
	        	Osprey_ReEu.payPal_Payment("PaypalDetails");
        
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
