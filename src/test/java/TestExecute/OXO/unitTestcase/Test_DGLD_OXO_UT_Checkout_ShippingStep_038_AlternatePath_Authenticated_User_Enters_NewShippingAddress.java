package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Checkout_ShippingStep_038_AlternatePath_Authenticated_User_Enters_NewShippingAddress {
	String datafile = "Oxo//OxoTestData.xlsx";
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_AuthenticatedUser_Enters_NewshippingAddress() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.headerlinks("Kitchenware");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.validate_registeruser_Shippingaddresspage();
			Oxo.Click_NewAddressCTA();
			Oxo.Validate_RegisterUser_shippingaddressform();
			Oxo.populate_Shippingaddress_fields("EditAddress");
			Oxo.Validate_Update_NewAddress_Verification("EditAddress");
			
			

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
