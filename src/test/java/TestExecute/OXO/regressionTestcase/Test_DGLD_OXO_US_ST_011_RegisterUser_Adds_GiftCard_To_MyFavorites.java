package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_US_ST_011_RegisterUser_Adds_GiftCard_To_MyFavorites {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_user_Adds_GiftCard_To_MyFavorites_and_checkout() throws Exception {
	

		try {
			Oxo.verifingHomePage();
			Oxo.Gift_cards("Oxo Gift Card");
			Oxo.Card_Value_for_my_fav("price");
			Oxo.Reg_Add_Wishlist_Create_account();
			Oxo.click_Createaccount();
			Oxo.create_account("New Account Details");
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
