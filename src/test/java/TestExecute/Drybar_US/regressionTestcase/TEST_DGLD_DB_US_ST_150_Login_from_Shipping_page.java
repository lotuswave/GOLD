package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_150_Login_from_Shipping_page {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"My AccountPage");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Login_from_Shipping_page () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.minicart_Checkout();
			//Drybar.Signin_Checkoutpage("AccountDetails");
			Drybar.click_singin_Shippingpage();
			Drybar.login_Drybar("AccountDetails");
			String newaddress= Drybar.shipping_new_Address("BillingDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			String rewardpoints = Drybar.Twenty_percent_Reward_Points("$20 Off (400 points)");
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			Drybar.Verify_Address(newaddress);
			

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
