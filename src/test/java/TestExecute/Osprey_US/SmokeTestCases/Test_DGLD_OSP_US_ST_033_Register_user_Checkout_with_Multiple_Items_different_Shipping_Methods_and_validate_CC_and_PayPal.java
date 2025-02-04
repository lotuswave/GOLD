package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_033_Register_user_Checkout_with_Multiple_Items_different_Shipping_Methods_and_validate_CC_and_PayPal {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_user_Checkout_with_2Day_Shipping_method () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
//        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.simple_addtocart("Simple product"); 
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("Bestway method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Gift_card("Partial Giftcard");
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCAmexcard");
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
