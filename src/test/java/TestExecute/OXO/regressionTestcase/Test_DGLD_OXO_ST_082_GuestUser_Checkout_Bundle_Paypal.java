package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_082_GuestUser_Checkout_Bundle_Paypal {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Checkout_Bundle_Paypal() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.search_product("Bundle");
			Oxo.addtocart("Bundle");
//			Oxo.search_product("Bundle1");
//			Oxo.addtocart("Bundle1");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress("AccountDetails");
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

