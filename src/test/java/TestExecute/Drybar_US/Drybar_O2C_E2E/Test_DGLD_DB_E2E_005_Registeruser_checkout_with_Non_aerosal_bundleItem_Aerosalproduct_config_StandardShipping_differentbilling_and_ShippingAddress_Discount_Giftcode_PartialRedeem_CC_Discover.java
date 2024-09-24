package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_005_Registeruser_checkout_with_Non_aerosal_bundleItem_Aerosalproduct_config_StandardShipping_differentbilling_and_ShippingAddress_Discount_Giftcode_PartialRedeem_CC_Discover {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Registeruser_checkout_Non_aerosal_bundleItem_Aerosalproduct_config_StandardShipping_ () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description="Register user checkout with Non aerosal  bundle item + Aerosal product + config +Standard Shipping + use different billing and Shipping address + Discount+ Gift code partial redeem + CC Discover";
		Drybar.Verify_Homepage();
        Drybar.click_singinButton();
        Drybar.login_Drybar("AccountDetails");
        Drybar.search_product("900-3020-1 Product");
        Drybar.addtocart("900-3020-1 Product"); 
        Drybar.search_product("Refresh Bundle");
        Drybar.addtocart("Refresh Bundle");
        Drybar.search_product("900-2930-1 Product");
        Drybar.addtocart("900-2930-1 Product");
        Drybar.minicart_Checkout();
        Drybar.RegaddDeliveryAddress("AccountDetails");
        Drybar.discountCode("Discount");
        Drybar.BillingAddress("AccountDetails");
        Drybar.selectshippingmethod("StandardShipping method");
        String Used_GiftCode = Drybar.Gift_Card_Enter("DRY-PRPD-TEST-QA"); 
        String OrderNumber=Drybar.updatePaymentAndSubmitOrder("CCMastercard");
        Drybar.Admin_signin("AccountDetails");
        Drybar.click_Sales();
        HashMap<String, String> Orderstatus1= Drybar.order_verfication(OrderNumber);
        Drybar.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);
        
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


