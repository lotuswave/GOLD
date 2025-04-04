package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_002_Registeruser_checkout_with_MultipleItems_standard_Shiping_with_Aerosal_Product_Gift_code_Partial_Redeem_CCMaster_PO_Box_address {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void VerifyingRegisteruser_checkout_with_MultipleItems_standard_Shiping_with_Aerosal_Product_Gift_code_Partial_Redeem_CCMaster_PO_Box_address () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description="Register user checkout with Multiple Items +  Aerosal product + Standard Shipping + Gift code Partial redeem +  CC Master + P.O Box address";
		Drybar.Verify_Homepage();
        Drybar.click_singinButton();
        Drybar.login_Drybar("AccountDetails");
        Drybar.search_product("900-2935-1 Product");
        Drybar.addtocart("900-2935-1 Product");
        Drybar.search_product("900-0700-4 Product");
        Drybar.addtocart("900-0700-4 Product");
        Drybar.search_product("900-2230-1 Product");
        Drybar.addtocart("900-2230-1 Product");
        Drybar.search_product("900-0630-1 Product");
        Drybar.addtocart("900-0630-1 Product");
        Drybar.minicart_Checkout();
        Drybar.RegaddDeliveryAddress("PO Box Address");
        Drybar.selectshippingmethod("StandardShipping method");
        String Used_GiftCode = Drybar.gitCard("GiftCode Partial Redeem");
    	HashMap<String,String> Details=Drybar.ordersummary_Details();
//        Drybar.clickSubmitbutton_Shippingpage();
//        Drybar.tax_validation_Paymentpage("Address");
        String OrderNumber=Drybar.updatePaymentAndSubmitOrder("CCMastercard");
//      Drybar.Admin_signin("AccountDetails");
//      Drybar.click_Sales();
//      HashMap<String, String> Orderstatus1= Drybar.order_verfication(OrderNumber);
      Drybar.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));
       
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
//        Drybar.close_add();
        

	}

}


