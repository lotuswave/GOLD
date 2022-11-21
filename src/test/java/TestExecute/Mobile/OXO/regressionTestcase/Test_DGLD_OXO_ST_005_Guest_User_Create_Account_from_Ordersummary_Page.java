package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestComponent.OXO.GoldOxoHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_005_Guest_User_Create_Account_from_Ordersummary_Page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper_Mobile Oxo=new GoldOxoHelper_Mobile(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Create_Account_from_Ordersummary_Page() throws Exception {

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
		//Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 String device=System.getProperty("dev","IOS");
			System.setProperty("configFile", "oxo/mobile_config.properties");
			if(device.equalsIgnoreCase("ios")) {
				System.setProperty("configFile", "oxo/mobile_config_ios.properties");
			}
			  Login.mobilesignIn(device);
			  Oxo.acceptPrivacy();
		}

}
