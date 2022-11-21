package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_046_Register_Login_from_shipping_Page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_Login_from_shipping_Page() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.search_product("Product");
			Oxo.addtocart_PLP("Product");
			Oxo.minicart_Checkout();
			Oxo.Signin_Checkoutpage("AccountDetails");
			Oxo.selectStandedshippingaddress();
			Oxo.ordersummary_validation();
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
		 String device=System.getProperty("dev","IOS");
			System.setProperty("configFile", "oxo/mobile_config.properties");
			if(device.equalsIgnoreCase("ios")) {
				System.setProperty("configFile", "oxo/mobile_config_ios.properties");
			}
			  Login.mobilesignIn(device);
			  Oxo.acceptPrivacy();
		}


}