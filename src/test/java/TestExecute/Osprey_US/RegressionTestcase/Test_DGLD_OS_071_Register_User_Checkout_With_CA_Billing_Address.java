package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_071_Register_User_Checkout_With_CA_Billing_Address {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_User_Checkout_With_CA_Billing_Address () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.click_singinButton();
		Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.search_product("poco Product");
        Osprey_ReEu.addtocart("poco Product");
        Osprey_ReEu.search_product("Ace Product");
        Osprey_ReEu.addtocart("Ace Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();
        Osprey_ReEu.Reg_BillingAddress("CABilling Details");
        Osprey_ReEu.proAce_Error_Payment("CCAmexcard");
        Osprey_ReEu.Shoppingcart_page();
        Osprey_ReEu.deleteProduct_shoppingcart(); 
        
        
        
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
