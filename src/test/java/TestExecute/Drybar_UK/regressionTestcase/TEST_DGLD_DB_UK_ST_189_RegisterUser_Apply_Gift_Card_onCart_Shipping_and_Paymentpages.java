package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_189_RegisterUser_Apply_Gift_Card_onCart_Shipping_and_Paymentpages {

	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Apply_Gift_Card_onCart_Shipping_and_Paymentpages () throws Exception {

		try {
			
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.minicart_viewcart();
			Drybar.gitCard("Partial GiftCode");
			Drybar.Cancel_Giftcard();
			Drybar.minicart_Checkout();
			Drybar.gitCard("Partial GiftCode");
			Drybar.Cancel_Giftcard();
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.gitCard("Partial GiftCode");
			Drybar.Cancel_Giftcard();
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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
