package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_006_PlaceOrder_Validate_Shipping_Method_for_Pro_subtotal_amount_lessthan100_Standard_Shipping10 {
	
	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_PlaceOrder_Validate_Shipping_Method_for_Pro_subtotal_amount_lessthan100_Standard_Shipping10 () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("prouser");
        Osprey_ReEu.search_product("Product<50");
        Osprey_ReEu.simple_addtocart("Product<50"); 
        Osprey_ReEu.minicart_Checkout();
 //       Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.Verify_Prouser_Shipping_lessthan100();
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
