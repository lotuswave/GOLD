package TestExecute.Holley;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Holley.GoldHolleyHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_Holley_001_Guest_User_Checkout_With_Invalid_CC {

	String datafile = "Holley//GoldHolleyTestData.xlsx";
	GoldHolleyHelper Holley = new GoldHolleyHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_card () throws Exception {

		try {
			Holley.verifingHomePage();
			Holley.Ac_and_Heating_Category("AC Condensors and Evaporators"); 
			Holley.addtocart("Product"); 
			Holley.shoppingcart_Checkout();
			Holley.Guest_delivery_Address("AccountDetails");
			Holley.selectshippingmethod("2 Day method");
			Holley.updtePayementcrditcard_WithInvalidData("PaymentDetails");
				
			


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
		System.setProperty("configFile", "Holley\\config.properties");
        Login.signIn();


	}

}
