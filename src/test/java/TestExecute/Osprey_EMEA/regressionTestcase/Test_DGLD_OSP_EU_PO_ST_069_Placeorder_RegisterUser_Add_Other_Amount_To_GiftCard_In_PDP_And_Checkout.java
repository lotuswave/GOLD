package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_EU_PO_ST_069_Placeorder_RegisterUser_Add_Other_Amount_To_GiftCard_In_PDP_And_Checkout {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Giftcard Payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RegisterUser_Add_Other_Amount_To_GiftCard_In_PDP_And_Checkout  () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.click_singinButton();
			Osprey_ReEu.Login_Account("Account");
			Osprey_ReEu.Gift_cards("Osprey Gift Card");
			Osprey_ReEu.Other_Amount("price");
			Osprey_ReEu.minicart_Checkout();
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
