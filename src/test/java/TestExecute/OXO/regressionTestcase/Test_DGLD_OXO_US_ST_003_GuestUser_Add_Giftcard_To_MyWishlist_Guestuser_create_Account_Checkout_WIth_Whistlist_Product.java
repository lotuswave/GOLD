package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_US_ST_003_GuestUser_Add_Giftcard_To_MyWishlist_Guestuser_create_Account_Checkout_WIth_Whistlist_Product {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUserCC_Add_Giftcard_To_MyWishlist_create_Account() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.Gift_cards("Giftcard");
			Oxo.Card_Value_for_my_fav("price");
			Oxo.Guest_Add_Wishlist_Create_account();
			Oxo.Giftcard_Add_from_My_fav("price");
			Oxo.minicart_Checkout();
			Oxo.addBillingDetails_PaymentDetails_SubmitOrder("CCAmexcard");
			
			
			

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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}

}
