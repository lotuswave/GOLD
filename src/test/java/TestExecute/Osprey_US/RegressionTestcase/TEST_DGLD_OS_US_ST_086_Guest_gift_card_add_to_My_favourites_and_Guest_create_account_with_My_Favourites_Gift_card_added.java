package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_ST_086_Guest_gift_card_add_to_My_favourites_and_Guest_create_account_with_My_Favourites_Gift_card_added {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guest_gift_card_add_to_My_favourites_and_Guest_create_account_with_My_Favourites_Gift_card_added () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Gift_cards("Osprey Gift Card");
        Osprey_ReEu.Card_Value_for_my_fav("price");
        Osprey_ReEu.Guest_Add_Wishlist_Create_account();
        Osprey_ReEu.Giftcard_Add_from_My_fav("price");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addBillingDetails_PaymentDetails_SubmitOrder("CCAmexcard");
//        Osprey_ReEu.giftCardSubmitOrder();
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
