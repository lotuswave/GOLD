package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_110_PROCustomer_Checkout_with_Multiple_Items_using_CC_with_Gift_Code_Partial {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_PROCustomer_Checkout_with_Multiple_Items_using_CC_with_Gift_Code_Partial () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("prouser");
        Osprey_ReEu.search_product("Product");     
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.Prouser_Discount();
        Osprey_ReEu.Accessories_Header("Accessories");
        Osprey_ReEu.simple_addtocart("Simple product");  
        Osprey_ReEu.Prouser_Discount();
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("ProShippingMethod");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Gift_card("Partial Giftcard-1");
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
