package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_007_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount {

	String datafile = "OXO//GoldOxoTestData.xlsx";
	GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.Account_page_Validation("Account");
			Oxo.search_product("Product");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			Oxo.register_billingAddress("BillingDetails");
			Oxo.discountCode("Discount");
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
			Oxo.signout();
			

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
		System.setProperty("configFile", "oxo\\config.properties");
		Login.signIn();
		Oxo.acceptPrivacy();
	}

}
