package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_ST_095_Register_user_adds_a_giftcard_to_the_Wishlist {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Register_user_adds_a_giftcard_to_the_Wishlist () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.Gift_cards("Osprey Gift Card");
        Osprey_ReEu.Card_Value_for_my_fav("price");
        Osprey_ReEu.Guest_Add_Wishlist_Create_account();
        Osprey_ReEu.Giftcard_Add_from_My_fav("price");
        Osprey_ReEu.minicart_Checkout();
    	Osprey_ReEu.addBillingDetails_PaymentDetails_SubmitOrder("CCVisacard");
        
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
