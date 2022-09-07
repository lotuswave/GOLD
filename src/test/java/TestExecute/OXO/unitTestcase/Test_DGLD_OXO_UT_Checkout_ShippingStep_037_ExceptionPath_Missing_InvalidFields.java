package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Checkout_ShippingStep_037_ExceptionPath_Missing_InvalidFields {
	

	String datafile = "Oxo//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AuthenticatedUser_Enters_NewshippingAddress() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.headerlinks("Kitchenware");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.Validate_invalid_missing_fileds_shippingpage("shippingpagerrormsg");	
			Oxo.validate_ExistingUser_Login_Checkoutpage("AccountDetails");
			Oxo.Validate_Valid_Signin_Checkoutpage();
			Oxo.Click_NewAddressCTA();
			Oxo.Validate_invalid_missing_fileds_shippingpage("shippingpagerrormsg");
		

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
