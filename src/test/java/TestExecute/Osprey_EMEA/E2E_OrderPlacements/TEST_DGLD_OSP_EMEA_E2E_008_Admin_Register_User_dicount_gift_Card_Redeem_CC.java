package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2E_HYVA;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_EMEA_E2E_008_Admin_Register_User_dicount_gift_Card_Redeem_CC {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_E2E_HYVA Osprey_ReEu = new OspreyEMEA_E2E_HYVA(datafile,"Osprey_E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Admin_Register_User_dicount_gift_Card_Redeem_CC  () throws Exception {

		try {
		Osprey_ReEu.prepareOrdersData1("OspreyEU_Admin_E2E_orderDetails.xlsx");	
		String Description ="Full Gift Code Redemption + Multiple Item + Multiple Qty";
        Osprey_ReEu.Admin_signin("Login Details");
        Osprey_ReEu.Customers();
        Osprey_ReEu.Allcustomers();
        Osprey_ReEu.SelectCustomer_Edit("OSP_Store");
        Osprey_ReEu.Click_CreatNewOrders();
        Osprey_ReEu.Select_store("OSP_Store");
        Osprey_ReEu.Add_product_SKU("OSP_Store");
        Osprey_ReEu.Admin_Discount("Discount");
        String Used_GiftCode=Osprey_ReEu.Admin_Giftcard("Partial Giftcard");
        Osprey_ReEu.Guestuser_shippingaddress("Account");
        Osprey_ReEu.Select_Shipping_method("Account");
        String OrderNumber= Osprey_ReEu.Default_Payment_method("CCAmexcard");
        Osprey_ReEu.click_Sales();
		HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
		Osprey_ReEu.writeOrderNumber1(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode,Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
		
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn(); 

	}

}
