package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_US_ST_012_Placeorder_GuestUser_Checkout_with_GiftCard_XMAS_GIFT_CARD {


	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_user_Configure_gift_card_in_PDP_and_checkout() throws Exception {
	

		try {
			Oxo.verifingHomePage();
			Oxo.Gift_cards("Oxo Gift Card");
			Oxo.Card_Value("price");
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
