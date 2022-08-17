package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Guest_Checkout_Funtionality {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Normal_Path_User_Views_Order_Summary_During_Checkout_ShippingStep() throws Exception {

		try {
			Hydro.verifingHomePage();
//			Hydro.headerlinks("Accessories");       //use in stage
//			Hydro.addtocart("Product");                    //use in stage
			Hydro.shop_QAtest("QA_Testing");
			Hydro.QAtest_addtocart_pdp("qa testing");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress("AccountDetails");
//			Hydro.addPaymentDetails("PaymentDetails");
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
