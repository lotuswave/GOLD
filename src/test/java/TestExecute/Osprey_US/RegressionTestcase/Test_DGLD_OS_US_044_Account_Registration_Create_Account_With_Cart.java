package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_044_Account_Registration_Create_Account_With_Cart {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"MyFavorites");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Account_Registration_Create_Account_With_Cart () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");    
        Osprey_ReEu.MyFavorites_Guestuser("Product");
        String Items=Osprey_ReEu.minicart_items();
        Osprey_ReEu.click_Createaccount();
        Osprey_ReEu.create_account("New Account Details");
        Osprey_ReEu.minicart_products(Items);
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
 //     Osprey_ReEu.clickSubmitbutton_Shippingpage();
        String Billing=Osprey_ReEu.BillingAddress("BillingDetails");
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCVisacard");
        Osprey_ReEu.verify_BillingAddress(Billing);
  
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
