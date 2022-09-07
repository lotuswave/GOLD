package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Checkout_ShippingStep_041_AlternatePath_AuthenticatedUser_Has_No_Saved_Addresses {

	String datafile = "Oxo//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AuthenticatedUser_Has_No_Saved_Addresses() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.validateCreateAccountpageNavigation();
			Oxo.validateaccountcreationpassword("AccountDetails");
			Oxo.headerlinks("Kitchenware");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.Validate_shippingaddressform();
			Oxo.populate_Shippingaddress_fields("AccountDetails");
			Oxo.selectshippingmethod("AccountDetails");
			Oxo.Validate_Paymentpage();

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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}

}
