package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_005_Guest_User_Create_Account_from_Ordersummary_Page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
//	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"DataSet");
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Create_Account_from_Ordersummary_Page() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.minicart_Checkout();
			Oxo.newuseraddDeliveryAddress("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
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
		  Oxo.acceptPrivacy();
	}

}
