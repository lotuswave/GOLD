package TestExecute.Mobile.Hydroflask.regressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_024_Add_To_Cart_and_Checkout_from_My_Favorites {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHelper_Mobile Hydro = new GoldHydroHelper_Mobile(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Add_To_Cart_and_Checkout_from_My_Favorites () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.My_Favorites();
			Hydro.Addtocart_From_MyFavorites("Product");
			Hydro.addDeliveryAddress_registerUser("AccountDetails");
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			

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
		System.setProperty("configFile", "Hydroflask/mobile_config.properties");
		if(device.equalsIgnoreCase("ios")) {
			System.setProperty("configFile", "Hydroflask/mobile_config_ios.properties");
		}
		  Login.mobilesignIn(device);
        Hydro.close_add();
	}

}
