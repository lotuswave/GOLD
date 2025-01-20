package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_005_Register_User_Checkout_with_different_Items_with_Amex_Card {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_User_Checkout_with_different_Items_with_Amex_Card () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Register_User_Checkout_with_different_Items_with_Amex_Card";
			String Used_GiftCode = "NULL";
            Hydro.Admin_signin("Login Details");
            Hydro.Customers();
            Hydro.Allcustomers();
            Hydro.SelectCustomer_Edit("HF_Store");
            Hydro.Click_CreatNewOrders();
            Hydro.Select_store("HF_Store");
            Hydro.Add_product_SKU("HF_Store");
            Hydro.Guestuser_shippingaddress("AccountDetails");
            Hydro.Select_Shipping_method("AccountDetails");
            String OrderNumber= Hydro.Default_Payment_method("CCAmexcard");
            Hydro.click_Sales();
			HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(OrderNumber);
			Hydro.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode);

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
