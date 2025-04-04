package TestExecute.Hydroflask.Archive;

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
			Hydro.prepareOrdersData1("HYF_Admin_E2E_orderDetails.xlsx");
			String Description ="CS order via Admin with Amex (Standard) - 3 differents Items, each qty -2";
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
			Hydro.writeOrderNumber1(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode,Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));

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
