package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestComponent.OXO.GoldOxoHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_006_Account_Registration_Create_Account_With_Cart {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper_Mobile Oxo=new GoldOxoHelper_Mobile(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Registration_Create_Account_With_Cart() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
//			Oxo.minicart_Checkout();
			String minicart = Oxo.minicart_items();
			Oxo.click_Createaccount();
			Oxo.create_account("AccountDetails");
			Oxo.minicart_products(minicart);
			
			
	

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

