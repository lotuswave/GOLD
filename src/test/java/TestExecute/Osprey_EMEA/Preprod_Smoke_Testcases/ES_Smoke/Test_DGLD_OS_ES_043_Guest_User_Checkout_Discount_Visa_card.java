package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.ES_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_ES_043_Guest_User_Checkout_Discount_Visa_card {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_User_Checkout_Visa_Card () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addDeliveryAddress_Guestuser("es_Address");
        Osprey_ReEu.selectshippingmethod("ES shipping method");
        Osprey_ReEu.discountCode("Discount");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
        
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
		String url = "https://mcloud-na-preprod.osprey.com/es/";
		System.setProperty("url", url);
        Login.signIn();
        

	}

}
