package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_002_Register_user_Checkout_Funtionality_Visa_card {

	String datafile = "Hydroflask//HydroData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_user_Checkout_Funtionality_Visa_card () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.headerlinks("Accessories");       //use in stage
			Hydro.addtocart("Product");                    //use in stage
//			Hydro.shop_QAtest("QA_Testing");
//			Hydro.QAtest_addtocart_pdp("qa testing");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_registerUser("AccountDetails");
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
		System.setProperty("configFile", "Hydroflask\\config.properties");

		Login.signIn();

	}

}
