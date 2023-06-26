package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_044_Account_Registration_Create_Account_With_Cart {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Account_Registration_Create_Account_With_Cart () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");    //need to add one more product from the header links
        Osprey_ReEu.MyFavorites_Guestuser("Product");
        String Items=Osprey_ReEu.minicart_items();
        Osprey_ReEu.click_Createaccount();
        Osprey_ReEu.create_account_with_fav("Create Account");
        Osprey_ReEu.minicart_products(Items);
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Billing=Osprey_ReEu.BillingAddress("BillingDetails");
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
        Osprey_ReEu.verify_BillingAddress(Billing);
  
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
