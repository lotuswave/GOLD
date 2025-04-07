package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_017_GuestUser_with_multiple_items_Gift_card_standard_shipping_CC {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_GuestUser_with_multiple_items_Gift_card_standard_shipping_CC () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Guest User with multiple  items + Gift card+ standard shipping +CC";
			Hydro.verifingHomePage();
			Hydro.search_product("SKU-P-S21001 - 1"); 
			Hydro.Myhydro_Graphic("SKU-P-S21001 - 1");
			Hydro.enraving_Checkout("Graphic");
			Hydro.search_product("SKU-TT40PS474");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS474");
			Hydro.search_product("SKU-CFX001");     
			Hydro.addtocart("SKU-CFX001");
			Hydro.search_product("SKU-BO56 -1QTY");     
			Hydro.addtocart("SKU-BO56 -1QTY");
			Hydro.Gift_cards("Hydro Gift Card");
			Hydro.Card_Value("price");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
			String Used_GiftCode = "NULL";
			Hydro.selectshippingaddress("GroundShipping method");
		    HashMap<String,String> Details=Hydro.ordersummary_Details();
	        String OrderNumber=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
          
			Hydro.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));

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
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}
