package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_143_Registeruser_Products_are_added_to_the_Wishlist_from_the_PLP_page_and_Checkout_from_the_ShoppingCart_page_with_Your_Favorites_Products {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registeruser_Products_are_added_to_the_Wishlist_from_the_PLP_page_and_Checkout_from_the_ShoppingCart_page_with_Your_Favorites_Products () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.My_Favorites();
			Drybar.Add_Favorites_from_PLP("PLP Product");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
			Drybar.Fav_Seeoption_from_View_cart("Plp Product");
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
		Common.closeAll();
		

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
