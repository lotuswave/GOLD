package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.ES_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_ES_BCT_007_Guest_user_Checkout_With_Discount_Visa_card {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_user_Checkout_Funtionality_With_Discount_Visa_card () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");                    
			Hydro.minicart_Checkout();
            Hydro.addDeliveryAddress_Guestuser("es_Address");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.discountCode("Discount");
            Hydro.clickSubmitbutton_Shippingpage();
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
		String url="https://mcloud-na-preprod.hydroflask.com/es";
		System.setProperty("url", url);
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
