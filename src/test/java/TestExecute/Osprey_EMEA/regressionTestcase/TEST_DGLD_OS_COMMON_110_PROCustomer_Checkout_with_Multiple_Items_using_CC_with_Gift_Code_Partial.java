package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_COMMON_110_PROCustomer_Checkout_with_Multiple_Items_using_CC_with_Gift_Code_Partial {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_PROCustomer_Checkout_with_Multiple_Items_using_CC_with_Gift_Code_Partial () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("prouser"); 
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.simple_addtocart("Simple product"); 
//        Osprey_ReEu.search_product("X-mas Gift Card");
//        Osprey_ReEu.Gift_cards("X-mas Gift Card");
//        Osprey_ReEu.Card_Value("price");  
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("ProShippingMethod");           //ProShippingMethod while executing us change this to proshipping method
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Gift_card("Partial Giftcard");
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
//        Osprey_ReEu.Partial_Payment("Partial Giftcard");
        
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
