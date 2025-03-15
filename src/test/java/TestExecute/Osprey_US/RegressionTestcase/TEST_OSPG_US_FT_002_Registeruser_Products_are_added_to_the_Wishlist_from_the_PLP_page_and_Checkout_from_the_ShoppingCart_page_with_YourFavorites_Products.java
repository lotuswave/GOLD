package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_OSPG_US_FT_002_Registeruser_Products_are_added_to_the_Wishlist_from_the_PLP_page_and_Checkout_from_the_ShoppingCart_page_with_YourFavorites_Products {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Registeruser_Products_are_added_to_the_Wishlist_from_the_PLP_page_and_Checkout_from_the_ShoppingCart_page_with_YourFavorites_Products () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.My_Favorites();
        Osprey_ReEu.Add_Favorites_from_PLP("Plp Product");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.click_minicart();
        Osprey_ReEu.minicart_viewcart();
        Osprey_ReEu.Fav_Seeoption_from_View_cart("Plp Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("ExpeditedShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");

        
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
