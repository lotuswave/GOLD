package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
			Drybar.search_product("Detox_SUB_Product"); 
			Drybar.subcribe_product_Add_to_Cart("Detox_SUB_Product");
		    Drybar.search_product("Sparkling Soda Shine Mist & UV Shield");  
		    Drybar.subcribe_product_Add_to_Cart("Sparkling Soda Shine Mist & UV Shield");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");	
			String rewardpoints=Drybar.Twentypercent_Reward_Points("$20 Off (400 points)");;
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			String Profile_id="6";
			String profileID_Number=Drybar.Verify_Profile_ids(Profile_id);
			Drybar.search_product("Triple_SUB_Product");
			Drybar.selecting_the_Previous_Subscription("Triple_SUB_Product");
			Drybar.add_To_Subscription(profileID_Number);
		

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
