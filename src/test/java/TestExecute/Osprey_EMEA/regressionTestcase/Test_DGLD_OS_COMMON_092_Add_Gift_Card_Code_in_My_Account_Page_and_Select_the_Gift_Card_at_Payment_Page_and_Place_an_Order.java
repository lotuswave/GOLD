package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_092_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_at_Payment_Page_and_Place_an_Order {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA  Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_register_user_Add_Gift_Code_My_Account_And_Place_an_Order () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.click_singinButton();
			Osprey_ReEu.Login_Account("Account");
			Osprey_ReEu.Add_GiftCode_Myaccount("Giftcard");
			Osprey_ReEu.search_product("Product");
			Osprey_ReEu.addtocart("Product");  
			Osprey_ReEu.minicart_Checkout();
			Osprey_ReEu.RegaddDeliveryAddress("Account");
			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			Osprey_ReEu.Select_Gift_Code("Giftcard");
			Osprey_ReEu.giftCardSubmitOrder();
			Osprey_ReEu.Remove_GiftCode();

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
