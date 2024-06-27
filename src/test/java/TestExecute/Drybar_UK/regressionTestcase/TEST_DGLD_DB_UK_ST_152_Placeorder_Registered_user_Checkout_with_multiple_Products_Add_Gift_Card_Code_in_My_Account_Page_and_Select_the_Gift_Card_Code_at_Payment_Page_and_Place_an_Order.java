package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_152_Placeorder_Registered_user_Checkout_with_multiple_Products_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Placeorder_Registered_user_Checkout_with_multiple_Products_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.Add_GiftCode_Myaccount("GiftCode Full Redeem");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.search_product("Product_2");  
			Drybar.addtocart("Product_2");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.Select_Gift_Code("GiftCode Full Redeem");
			Drybar.giftCardSubmitOrder();
			Drybar.Remove_GiftCode();
			

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
