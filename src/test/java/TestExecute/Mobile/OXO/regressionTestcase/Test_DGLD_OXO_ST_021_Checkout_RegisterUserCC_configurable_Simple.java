package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestComponent.OXO.GoldOxoHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_021_Checkout_RegisterUserCC_configurable_Simple {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper_Mobile Oxo = new GoldOxoHelper_Mobile(datafile,"DataSet");	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Register_User_Checkout_configurable_Simple() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("Product");
			Oxo.addtocart_PLP("Product");
			Oxo.babytoddler_headerlinks("Baby & Toddler");
			Oxo.Configurable_addtocart_pdp("ConfigProduct");
//			Oxo.search_product("ConfigProduct1");
//			Oxo.Configurable_addtocart_pdp("ConfigProduct1");
//			Oxo.search_product("ConfigProduct2");
//			Oxo.Configurable_addtocart_pdp("ConfigProduct2");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
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
