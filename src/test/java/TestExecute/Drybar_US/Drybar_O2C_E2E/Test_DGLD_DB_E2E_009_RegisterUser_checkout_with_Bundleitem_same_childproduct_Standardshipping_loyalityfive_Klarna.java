package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_009_RegisterUser_checkout_with_Bundleitem_same_childproduct_Standardshipping_loyalityfive_Klarna {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RegisterUser_checkout_with_Bundleitem_same_childproduct_Standardshipping_loyalityfive_Klarna () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description="Register User checkout with Bundle item + same child product + Standard shipping + loyality 5$ + Klarna";
		Drybar.Verify_Homepage();
        Drybar.click_singinButton();
        Drybar.login_Drybar("AccountDetails");
        Drybar.search_product("900-0700-4 Product");
        Drybar.addtocart("900-0700-4 Product");
        Drybar.search_product("Perfect blow Bundle E2E");
        Drybar.addtocart("Perfect blow Bundle E2E");
        Drybar.minicart_Checkout();
        Drybar.RegaddDeliveryAddress("AccountDetails");
        Drybar.selectshippingmethod("GroundShipping method");
        Drybar.fivepercent_Reward_Points("$5 Off (100 points)");
        Drybar.clickSubmitbutton_Shippingpage();
       // Drybar.tax_validation_Paymentpage("Address");
        String Used_GiftCode = "NULL"; // Indicating no gift code used 
    	HashMap<String,String> Details=Drybar.ordersummary_Details();
        Drybar.Kalrna_Payment("Klarna Visa Payment");
        String OrderNumber=Drybar.ordernumber();
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
        //Drybar.close_add();
        

	}

}


