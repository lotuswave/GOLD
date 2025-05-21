package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_066_RegisterUser_Add_Other_Amount_To_GiftCard_In_PDP_And_Checkout {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
public void Validating_RegisterUser_Add_Other_Amount_To_GiftCard_In_PDP_And_Checkout () throws Exception {
	
	try {
		Hydro.verifingHomePage();
		Hydro.search_Gift_Card();
//		Hydro.Gift_cards("Hydro Gift Card");
		Hydro.Other_Amount("price");
	    Hydro.minicart_Checkout();
		Hydro.DeliveryAddress_Guestuser_Gift("Gift_CardAccountDetails");
		Hydro.updatePaymentAndSubmitOrder("PaymentDetails");

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
	System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
	Login.signIn();
	Hydro.close_add();
    Hydro.acceptPrivacy();
}


}
