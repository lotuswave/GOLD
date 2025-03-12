package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_US_ST_006_GiftCard_And_ConfigurableProduct_Checkout_With_PromoCode_Applied {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_GuestUser_GiftCard_And_ConfigurableProduct_Checkout_With_PromoCode_CC () throws Exception {

		try {
        Hydro.verifingHomePage();
		Hydro.bottles_headerlinks("Bottles & Drinkware"); 
		Hydro.Configurable_addtocart_pdp("Product");
		Hydro.Gift_cards("Hydro Gift Card");
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}

}
