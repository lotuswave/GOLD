package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_014_GuestUser_checkout_with_paypal_MyHydro_inline_2Day {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_GuestUser_checkout_with_paypal_MyHydro_inline_2Day () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Guest user checkout with paypal + My Hydro + inline 2 Day";
			Hydro.verifingHomePage();
			Hydro.search_product("SKU-TT40PS474 -2QTY");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS474 -2QTY");
			Hydro.search_product("SKU-P-S21001 - 2QTY"); 
			Hydro.Add_Myhydro("SKU-P-S21001 - 2QTY");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("2 Day method");
            HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber=Hydro.payPal_Payment("PaypalDetails");
           
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
