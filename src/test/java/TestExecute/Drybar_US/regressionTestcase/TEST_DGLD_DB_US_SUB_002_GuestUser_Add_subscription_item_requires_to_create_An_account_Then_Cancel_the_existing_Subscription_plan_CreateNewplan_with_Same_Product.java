package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_SUB_002_GuestUser_Add_subscription_item_requires_to_create_An_account_Then_Cancel_the_existing_Subscription_plan_CreateNewplan_with_Same_Product {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_Checkout_Funtionality_Visa_card () throws Exception {

		try { 
		
			Drybar.Verify_Homepage();
		    Drybar.search_product("Save 10% with Daily Shipments");  
		    Drybar.subcribe_product_Add_to_Cart("Save 10% with Daily Shipments");
			Drybar.Guest_Sub_minicart_Checkout();
			Drybar.login_Drybar("AccountDetails");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			Drybar.Change_Subscription();
			Drybar.search_product("Save 15% with a Daily Shipment for 3 days");  
			Drybar.subcribe_product_Add_to_Cart("Save 15% with a Daily Shipment for 3 days");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
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
