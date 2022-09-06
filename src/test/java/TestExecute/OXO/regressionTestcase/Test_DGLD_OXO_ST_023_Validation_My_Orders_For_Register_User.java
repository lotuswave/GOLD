package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_023_Validation_My_Orders_For_Register_User {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_My_Orders_For_Register_User() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			String order = Oxo.updatePaymentAndSubmitOrder("CCDiscovercard");
			Oxo.My_Orders_Page(order);
			
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();

	}

}


