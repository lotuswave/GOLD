package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_CS_004_Register_User_Checkout_with_multiple_Items_and_Giftcard_Item_With_CC {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_User_Checkout_with_multiple_Items_and_Giftcard_Item_With_CC() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			String Description ="CS order via Admin with Amex (Standard) - 3 Items + Giftcard item + each qty -3";
			 String Used_GiftCode = "NULL";
			 Oxo.Admin_signin("Login Details");
			 Oxo.Customers();
			 Oxo.Allcustomers();
			 Oxo.SelectCustomer_Edit("OXO_Store");
			 Oxo.Click_CreatNewOrders();
			 Oxo.Select_store("OXO_Store");
			 Oxo.Add_product_SKU("OXO_Store");
			 Oxo.Configure_GiftCard("Gift Details");
			 Oxo.Guestuser_shippingaddress("AccountDetails");
			 Oxo.Select_Shipping_method("AccountDetails");
	         String OrderNumber= Oxo.Default_Payment_method("CCAmexcard");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode);
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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}

}
