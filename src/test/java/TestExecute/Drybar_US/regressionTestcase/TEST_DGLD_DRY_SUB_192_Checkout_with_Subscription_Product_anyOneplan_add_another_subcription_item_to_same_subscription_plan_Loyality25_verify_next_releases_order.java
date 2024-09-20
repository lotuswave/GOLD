package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DRY_SUB_192_Checkout_with_Subscription_Product_anyOneplan_add_another_subcription_item_to_same_subscription_plan_Loyality25_verify_next_releases_order {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_card () throws Exception {

		try { 
		
		    Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Same_10%_Plan_Diff_Prdt"); 
			Drybar.subcribe_product_Add_to_Cart("Same_10%_Plan_Diff_Prdt");
		    Drybar.search_product("Sparkling Soda Shine Mist & UV Shield");  
		    Drybar.subcribe_product_Add_to_Cart("Save 10% with Daily Shipments");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.TwentyFive_percent_Reward_Points("$25 Off (500 point)");
			Drybar.selectshippingmethod("GroundShipping method");
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
