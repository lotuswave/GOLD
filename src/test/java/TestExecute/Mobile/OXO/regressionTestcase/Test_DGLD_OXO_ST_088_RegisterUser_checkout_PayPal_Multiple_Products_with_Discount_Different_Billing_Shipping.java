package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestComponent.OXO.GoldOxoHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_088_RegisterUser_checkout_PayPal_Multiple_Products_with_Discount_Different_Billing_Shipping {


	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper_Mobile Oxo = new GoldOxoHelper_Mobile(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Checkout_With_Different_Billing_and_Different_Shipping() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.search_product("Product");
			Oxo.addtocart_PLP("Product");
			Oxo.babytoddler_headerlinks("Baby & Toddler");
			Oxo.Configurable_addtocart_pdp("ConfigProduct");
			Oxo.search_product("Bundle");
			Oxo.addtocart("Bundle");
//			Oxo.search_product("Group");
//			Oxo.addtocart("Group");
			Oxo.minicart_Checkout();
			Oxo.discountCode("Discount");
			Oxo.addDeliveryAddress("AccountDetails");
			Oxo.register_billingAddress("BillingDetails");
			Oxo.payPal_Payment("PaypalDetails");

			
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
		 String device=System.getProperty("dev","IOS");
			System.setProperty("configFile", "oxo/mobile_config.properties");
			if(device.equalsIgnoreCase("ios")) {
				System.setProperty("configFile", "oxo/mobile_config_ios.properties");
			}
			  Login.mobilesignIn(device);
			  Oxo.acceptPrivacy();
		}
}

