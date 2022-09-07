package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_CheckoutPaymentStep_055_NormalPath_AuthenticatedUser_Selects_to_ChangeBillingAddress {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class, invocationCount=4)
	public void Validate_AuthenticatedUser_Selects_to_ChangeBillingAddress() throws Exception {

		try {
			Hydro.validate_Homepage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.remove_Allproducts_minicart();
//			Hydro.navigate_To_MyAccount();
			Hydro.setup_DefaultShipping_Billingaddress();
			Hydro.search_product_pdp("search");
			Hydro.minicart_Checkout();
			Hydro.Validate_Paymentpage("AccountDetails");
			Hydro.Updatebillingaddress("AccountDetails");
			Hydro.clickStoreLogo();
			
			
			
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
		Hydro.ClosADD();
	}

	

}
