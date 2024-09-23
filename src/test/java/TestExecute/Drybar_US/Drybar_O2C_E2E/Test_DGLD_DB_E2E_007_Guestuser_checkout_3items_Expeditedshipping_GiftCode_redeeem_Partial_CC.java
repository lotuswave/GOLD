package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_007_Guestuser_checkout_3items_Expeditedshipping_GiftCode_redeeem_Partial_CC {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Registeruser_checkout_with_MultipleItems_standard_Shiping_CCMaster_PO_Box_address () throws Exception {

		try {
			Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
			String Description="Guest user checkout with 3 items + Expedited shipping + Gift Code redeeem Partial + CC";
			Drybar.Verify_Homepage();
			Drybar.search_product("900-0700-4 Product");
	        Drybar.addtocart("900-0700-4 Product");
	        Drybar.search_product("900-2230-1 Product");
	        Drybar.addtocart("900-2230-1 Product");
	        Drybar.search_product("900-0630-1 Product");
	        Drybar.addtocart("900-0630-1 Product");
	        Drybar.minicart_Checkout();
	        String Used_GiftCode= Drybar.Gift_Card_Enter("DRY-PRPD-TEST-QA");
	        Drybar.addDeliveryAddress_Guestuser("Address");
	        Drybar.selectshippingmethod("ExpeditedShipping method");
	        Drybar.clickSubmitbutton_Shippingpage();
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


