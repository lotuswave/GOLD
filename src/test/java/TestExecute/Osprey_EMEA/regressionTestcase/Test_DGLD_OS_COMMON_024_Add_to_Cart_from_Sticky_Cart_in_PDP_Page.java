package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_024_Add_to_Cart_from_Sticky_Cart_in_PDP_Page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Add_to_Cart_from_Sticky_Cart_in_PDP_Page() throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Product");      // need to add from header links categories and need to change in the helper also
        Osprey_ReEu.Sticky_Add_to_Cart("Product");
        Osprey_ReEu.search_product("Simple product");
        Osprey_ReEu.Sticky_Add_to_Cart("Simple product");        
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Change_Shippingmethods("Bestway method");
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
