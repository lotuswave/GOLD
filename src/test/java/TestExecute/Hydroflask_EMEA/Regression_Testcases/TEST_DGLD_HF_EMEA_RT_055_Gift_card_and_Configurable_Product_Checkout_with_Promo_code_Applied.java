package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_055_Gift_card_and_Configurable_Product_Checkout_with_Promo_code_Applied {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_GuestUser_GiftCard_And_ConfigurableProduct_Checkout_With_PromoCode_CC () throws Exception {

		try {
        Hydro.verifingHomePage();
		Hydro.bottles_headerlinks("bottles-drinkware"); 
		Hydro.Configurable_addtocart_pdp("Product");
		Hydro.Gift_cards("Hydro Gift Card");//-
		Hydro.Card_Value("price");
		Hydro.minicart_Checkout();
		Hydro.addDeliveryAddress_Guestuser("AccountDetails");
        Hydro.selectshippingaddress("GroundShipping method");
        Hydro.clickSubmitbutton_Shippingpage();
        Hydro.discountCode("Discount");
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

