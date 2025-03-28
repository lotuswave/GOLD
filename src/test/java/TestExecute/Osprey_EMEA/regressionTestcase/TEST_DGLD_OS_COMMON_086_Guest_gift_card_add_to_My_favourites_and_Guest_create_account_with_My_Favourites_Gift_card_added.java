package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_COMMON_086_Guest_gift_card_add_to_My_favourites_and_Guest_create_account_with_My_Favourites_Gift_card_added {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guest_gift_card_add_to_My_favourites_and_Guest_create_account_with_My_Favourites_Gift_card_added () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Osprey Gift Card");
        Osprey_ReEu.Gift_cards("Osprey Gift Card");
        Osprey_ReEu.Card_Value_for_my_fav("price");
        Osprey_ReEu.Guest_Add_Wishlist_Create_account();
        Osprey_ReEu.Giftcard_Add_from_My_fav("price");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addBillingDetails_PaymentDetails_SubmitOrder("CCAmexcard");
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
