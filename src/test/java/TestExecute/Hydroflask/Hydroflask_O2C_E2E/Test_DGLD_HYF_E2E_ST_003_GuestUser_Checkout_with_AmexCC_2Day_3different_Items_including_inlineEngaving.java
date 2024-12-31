package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_003_GuestUser_Checkout_with_AmexCC_2Day_3different_Items_including_inlineEngaving {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_GuestUser_Checkout_with_AmexCC_2Day_3different_Items_including_inlineEngaving () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Guest user checkout with Amex CC - 2Day + 3 different Items including inline engaving, each qty -3";
			Hydro.verifingHomePage();
			Hydro.search_product("SKU-BC128001 -3QTY");     
			Hydro.addtocart("SKU-BC128001 -3QTY");
			Hydro.search_product("SKU-T28CPB612 -3QTY");     
			Hydro.Text_Engraving("SKU-T28CPB612 -3QTY");
			Hydro.search_product("SKU-K12474 -3QTY");     
			Hydro.Configurable_addtocart_pdp("SKU-K12474 -3QTY");;
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
			String Used_GiftCode = "NULL";
            Hydro.selectshippingaddress("GroundShipping method");
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("CCAmexcard");
            Hydro.Admin_signin("Login Details");
            Hydro.click_Sales();
			HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(OrderNumber);
			Hydro.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);

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
