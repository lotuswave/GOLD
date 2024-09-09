package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_067_Checkout_with_RegisterUserCC_Simple_Configurable_Bundle_products_GC_partialreedem_CC {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile, "Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Checkout_with_RegisterUserCC_Simple_Configurable_Bundle_products_GC_partialreedem_CC()
			throws Exception {

		try {

			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Bundle Product");
			Drybar.Addtocart_Bundle("Bundle Product");
			Drybar.search_product("Product");
			Drybar.addtocart("Product");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			String rewardpoints = Drybar.fivepercent_Reward_Points("$5 Off (100 points)");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.gitCard("GiftCode");
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");

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
