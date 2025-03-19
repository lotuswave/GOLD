package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_US_ST_011_Register_user_adds_a_gift_card_to_My_Favorites {
	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Register_user_adds_a_gift_card_to_My_Favorites () throws Exception {

		try {
        Hydro.verifingHomePage();
        Hydro.click_singinButton();
        Hydro.login_Hydroflask("AccountDetails");
        Hydro.Gift_cards("Hydro Gift Card");
        Hydro.Card_Value_for_my_fav("price");
        Hydro.Guest_Add_Wishlist_Create_account();
        Hydro.Giftcard_Add_from_My_fav("price");
        
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();
        

	}

}
