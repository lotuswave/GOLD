package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Checkout_PaymentStep_001_ExceptionPath_Missing_Invalid_CreditCard_Fields {
	String datafile = "Oxo//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_User_Enters_Missing_Invalid_CreditCard_Fields() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.headerlinks("Kitchenware");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddressShipping("AccountDetails");
			Oxo.updtePayementcrditcard_WithInvalidData("invalidPaymentDetails");
		

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
//		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "oxo\\config.properties");
		Login.signIn();

	}

}
