package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_MyOrderConfirmation_030_AlternatePath_Unauthenticated_User_Views_OrderConfirmation_Page_and_Creates_an_Account {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	OxoHelper Oxo=new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Views_OrderConfirmation_Page_and_Creates_an_Account() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.minicart_Checkout();
			Oxo.newuseraddDeliveryAddress("AccountDetails");
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
			Oxo.createAccountFromOrderSummaryPage("AccountDetails");
			
			
			

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
		  Oxo.closepromobanner();
		  Oxo.AcceptAll();

	}

}
