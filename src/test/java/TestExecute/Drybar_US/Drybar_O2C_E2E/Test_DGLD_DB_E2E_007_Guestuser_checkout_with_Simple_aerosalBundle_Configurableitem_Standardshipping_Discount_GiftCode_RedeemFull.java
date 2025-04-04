package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_007_Guestuser_checkout_with_Simple_aerosalBundle_Configurableitem_Standardshipping_Discount_GiftCode_RedeemFull {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guestuser_checkout_with_Simple_aerosalBundle_Configurableitem_Standardshipping_Discount_GiftCode_RedeemFull () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description="Guest user checkout with 1 Simple + 1 aerosal Bundle + 1Configurable  item + Standard shipping + Discount + Gift Code Redeem Full";
		Drybar.Verify_Homepage();
        Drybar.search_product("900-2230-1 Product");
        Drybar.addtocart("900-2230-1 Product");
        Drybar.search_product("CURE-LIQUEUR- product");
        Drybar.addtocart("CURE-LIQUEUR- product");
        Drybar.search_product("Refresh Bundle");
    	Drybar.addtocart("Refresh Bundle");
        Drybar.minicart_Checkout();
        Drybar.addDeliveryAddress_Guestuser("Address");
        Drybar.selectshippingmethod("StandardShipping method");
        Drybar.discountCode("Discount");
        String Used_GiftCode= Drybar.gitCard("GiftCode Full Redeem");
      	HashMap<String,String> Details=Drybar.ordersummary_Details();
        String OrderNumber=Drybar.giftCardSubmitOrder();
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
        Drybar.close_add();
        

	}

}


