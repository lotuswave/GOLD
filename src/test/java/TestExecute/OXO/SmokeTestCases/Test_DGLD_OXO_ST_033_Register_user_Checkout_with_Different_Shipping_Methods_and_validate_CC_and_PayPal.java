package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_033_Register_user_Checkout_with_Different_Shipping_Methods_and_validate_CC_and_PayPal {

String datafile = "OXO//GoldOxoTestData.xlsx";	
GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile,"DataSet");

@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
public void Validating_Register_user_Different_Shipping_Methods_and_validate_CC_and_PayPal() throws Exception {

	try {
		Oxo.verifingHomePage();
		Oxo.click_singinButton();
		Oxo.Usersignin("AccountDetails");
		Oxo.coffee_headerlinks("Coffee & Beverage");
		Oxo.addtocart("addproduct");
		Oxo.minicart_Checkout();
		Oxo.addDeliveryAddress_registerUser("AccountDetails");
		Oxo.select_Shipping_Method("2 Day method");
		Oxo.updatePaymentAndSubmitOrder("CCMastercard");
		Oxo.payPal_Payment("PaypalDetails");
		
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
	 System.setProperty("configFile", "oxo\\config.properties");
	  Login.signIn();
	  Oxo.acceptPrivacy();
}

}
