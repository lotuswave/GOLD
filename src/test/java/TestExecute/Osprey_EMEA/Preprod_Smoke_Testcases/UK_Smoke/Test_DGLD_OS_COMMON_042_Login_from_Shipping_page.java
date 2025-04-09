package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.UK_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_042_Login_from_Shipping_page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"My AccountPage");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Login_from_Shipping_page () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Simple product");
        Osprey_ReEu.simple_addtocart("Simple product");  
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.Signin_Checkoutpage("Account");
        String newaddress= Osprey_ReEu.shipping_new_Address("BillingDetails");
        Osprey_ReEu.Edit_Address_verify("Edit Address");    //need to change
        Osprey_ReEu.selectshippingmethod("GroundShipping method");    
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