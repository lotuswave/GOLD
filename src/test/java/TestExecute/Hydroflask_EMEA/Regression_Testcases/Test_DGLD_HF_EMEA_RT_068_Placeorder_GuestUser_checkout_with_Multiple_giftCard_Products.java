package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_EMEA_RT_068_Placeorder_GuestUser_checkout_with_Multiple_giftCard_Products {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Placeorder_GuestUser_checkout_with_Multiple_giftCard_Products () throws Exception {

		try {
        Hydro.verifingHomePage();
		Hydro.Gift_cards("Hydro Gift Card");
		Hydro.Different_Amount_Gift_Cards();
		Hydro.click_minicart();
		Hydro.minicart_Checkout();
		Hydro.DeliveryAddress_Guestuser_Gift("AccountDetails");
        Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
        
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}

