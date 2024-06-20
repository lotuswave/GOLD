package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_039_GuestUser_Add_product_from_pdp_to_wishlist_sign_in_and_checkout_from_wishlist_page {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Register_Products_are_added_to_the_Wish_list_from_the_PDP_page_and_Check_out_from_the_Shopping_Cart_page_with_Your_Favorites_Products () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.search_product("MyWishList");  
			Drybar.addto_MyWishList("MyWishList");
			Drybar.login_Drybar("AccountDetail");
			Drybar.My_Favorites();
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
			Drybar.Fav_Seeoption_from_View_cart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();
		

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
