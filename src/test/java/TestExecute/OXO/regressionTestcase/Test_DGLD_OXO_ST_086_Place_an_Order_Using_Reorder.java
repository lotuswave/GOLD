package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_086_Place_an_Order_Using_Reorder {

	String datafile = "OXO//GoldOxoTestData.xlsx";
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Place_an_Order_with_Reorder() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.reorder();
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");

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
