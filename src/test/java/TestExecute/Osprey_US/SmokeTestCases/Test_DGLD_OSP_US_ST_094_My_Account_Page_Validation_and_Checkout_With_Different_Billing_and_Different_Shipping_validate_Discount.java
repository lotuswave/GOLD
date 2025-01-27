package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUS_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_ST_094_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUS_PRODHyvaHelper Osprey_ReEu = new GoldOspreyUS_PRODHyvaHelper(datafile, "Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_My_Account_Page_Validation_and_Checkout_With_Different_Billing_and_Different_Shipping_validate_Discount() throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.click_singinButton();
	        Osprey_ReEu.Login_Account("Account");
			Osprey_ReEu.Account_page_Validation("Account validation");
			Osprey_ReEu.search_product("Product");
		    Osprey_ReEu.addtocart("Product");
		    Osprey_ReEu.minicart_Checkout();
		    Osprey_ReEu.RegaddDeliveryAddress("Account");
		    Osprey_ReEu.selectshippingmethod("GroundShipping method");
		    Osprey_ReEu.clickSubmitbutton_Shippingpage();
		    Osprey_ReEu.Reg_BillingAddress("BillingDetails");
	        Osprey_ReEu.discountCode("Discount");
	        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
	        Osprey_ReEu.signout();
	        

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
