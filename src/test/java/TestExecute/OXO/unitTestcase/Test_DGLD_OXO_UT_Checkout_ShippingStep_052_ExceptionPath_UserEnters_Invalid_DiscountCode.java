package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Checkout_ShippingStep_052_ExceptionPath_UserEnters_Invalid_DiscountCode {
	String datafile = "Oxo//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_User_Enters_Invalid_DiscountCode() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.headerlinks("Kitchenware");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddressShipping("AccountDetails");
			Oxo.invalid_DiscountCode("Discount");
			Oxo.valid_DiscountCode("Discount");
			

			

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
