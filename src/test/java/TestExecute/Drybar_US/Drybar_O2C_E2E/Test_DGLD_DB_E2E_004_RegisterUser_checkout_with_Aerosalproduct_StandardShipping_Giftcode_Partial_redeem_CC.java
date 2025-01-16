package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_004_RegisterUser_checkout_with_Aerosalproduct_StandardShipping_Giftcode_Partial_redeem_CC {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RegisterUser_checkout_with_Aerosalproduct_StandardShipping_Giftcode_ () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description="Register User checkout with Aerosal product + Standard Shipping + Gift code Partial redeem + CC";
		Drybar.Verify_Homepage();
        Drybar.click_singinButton();
        Drybar.login_Drybar("AccountDetails");
        Drybar.search_product("900-2935-1 Product");
        Drybar.addtocart("900-2935-1 Product");
        Drybar.minicart_Checkout();
        String Used_GiftCode =  Drybar.Gift_Card_Enter("DRYGOLD-QATEST-PRPD-02"); 
        Drybar.RegaddDeliveryAddress("AccountDetails");
        Drybar.selectshippingmethod("StandardShipping method");
       
        Drybar.clickSubmitbutton_Shippingpage();
       // Drybar.tax_validation_Paymentpage("Address");
        String OrderNumber=Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
        Drybar.Admin_signin("AccountDetails");
        Drybar.click_Sales();
        HashMap<String, String> Orderstatus1= Drybar.order_verfication(OrderNumber);
        Drybar.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode);
        
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


