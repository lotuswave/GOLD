package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_056__Guest_User_Checkout_with_Gift_Card_Code {
	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_User_Checkout_with_Gift_Card_Code () throws Exception {

		try {
        Hydro.verifingHomePage();
		Hydro.search_product("Product");     
		Hydro.addtocart("Product");
		Hydro.minicart_Checkout();
		Hydro.addDeliveryAddress_Guestuser("AccountDetails_2");
        Hydro.selectshippingaddress("GroundShipping method");
        Hydro.clickSubmitbutton_Shippingpage();
        Hydro.Gift_card("Full_RedeemGiftcard");
        Hydro.giftCardSubmitOrder();
        
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
