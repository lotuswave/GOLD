package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_OrderConfirmation_068_AdditionalPath_We_are_here_for_you_section {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AdditionalPath_We_are_here_for_you_section() throws Exception {

		try {
			Hydro.verifingHomePage();
			 
			Hydro.validateCreateAccountpageNavigation();
			Hydro.validateaccountcreationPassword("AccountDetails");
			
			Hydro.search_product_pdp("search");
			Hydro.minicart_Checkout();
			Hydro.populate_Shippingaddress_fields("AccountDetails");
			Hydro.Validate_Paymentpage("AccountDetails");
			Hydro.addPaymentDetails("PaymentDetails");
			Hydro.Validate_OrderConfirmationPage("SuccessPage");
			Hydro.Validating_We_are_here_for_you_section_Content("SuccessPage");
		    Hydro.Validating_We_are_here_for_you_section_hyperlink("SuccessPage");
	
			
		
			

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
		Hydro.AcceptAll();
	}
	
}


