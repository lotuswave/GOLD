package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_105_Pro_user_checkout_with_configurable_product_simple_with_discount {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Pro_user_checkout_with_configurable_product_simple_with_discount () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("prouser");
        Osprey_ReEu.search_product("Product");     
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.Prouser_Discount();
        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
        Osprey_ReEu.simple_addtocart("Simple product");   
        Osprey_ReEu.Prouser_Discount();
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("ProShippingMethod");    //ProShippingMethod while executing us change this to proshipping method
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
