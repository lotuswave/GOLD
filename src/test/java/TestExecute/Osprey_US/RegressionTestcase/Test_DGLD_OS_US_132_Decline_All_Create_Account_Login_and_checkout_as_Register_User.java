package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_132_Decline_All_Create_Account_Login_and_checkout_as_Register_User {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Decline_All_Create_Account_Login_and_checkout_as_Register_User  () throws Exception {

		try {
        Osprey_ReEu.Verify_HomePage();
        Osprey_ReEu.click_Createaccount();
        Osprey_ReEu.create_account("Create Account");
        Osprey_ReEu.search_product("Product");
        Osprey_ReEu.addtocart("Product");
        Osprey_ReEu.minicart_Checkout();
        Osprey_ReEu.RegaddDeliveryAddress("Account");
        Osprey_ReEu.selectshippingmethod("GroundShipping method");
        Osprey_ReEu.clickSubmitbutton_Shippingpage();      
        Osprey_ReEu.updatePaymentAndSubmitOrder("CCAmexcard");
        if(Common.getCurrentURL().contains("https://mcloud-na-preprod.osprey.com/")) {
        	System.out.println("Executed In PRE-PROD");
            Osprey_ReEu.signout();
            Osprey_ReEu.click_singinButton();
            Osprey_ReEu.Login_Account("Account");
        }else {
        	System.out.println("Executed In PROD");  	
        }
		
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
        Osprey_ReEu.Decline_All();
 
	}

}
