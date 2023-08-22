package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_085_Placeorder_GuestUser_Configure_giftCard_in_PDP_checkout_with_GiftCard_code {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Placeorder_GuestUser_Configure_giftCard_in_PDP_checkout_with_GiftCard_code () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Gift_cards("Birthday Gift Card");
        Osprey_ReEu.Card_Value("price");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.DeliveryAddress_Guestuser_Gift("Account");
        Osprey_ReEu.Gift_card("Giftcard");
        Osprey_ReEu.giftCardSubmitOrder();
        
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
