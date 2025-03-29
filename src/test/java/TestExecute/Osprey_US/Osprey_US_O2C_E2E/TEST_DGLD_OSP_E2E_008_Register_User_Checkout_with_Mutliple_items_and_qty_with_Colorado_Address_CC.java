package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_E2E_008_Register_User_Checkout_with_Mutliple_items_and_qty_with_Colorado_Address_CC {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSE2EHelper Osprey_ReEu = new GoldOspreyUSE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Checkout_with_Mutliple_items_and_qty_with_Colorado_Address_CC () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData1("OspreyUS_Admin_E2E_orderDetails.xlsx");
			 String Description ="Admin  user checkout with 4 Items - 2 QTY each -  from Colorado address with standard shipping  CC";
			  Osprey_ReEu.Admin_signin("Login Details");
			  Osprey_ReEu.Customers();
			  Osprey_ReEu.Allcustomers();
			  Osprey_ReEu.SelectCustomer_Edit("OSP_Store");
			  Osprey_ReEu.Click_CreatNewOrders();
			  Osprey_ReEu.Select_store("OSP_Store");
			  Osprey_ReEu.Add_product_SKU("OSP_Store");
			  Osprey_ReEu.Guestuser_shippingaddress("Colorado Address");
	          Osprey_ReEu.Select_Shipping_method("Colorado Address"); 
			  String Used_GiftCode="NULL";
	          String OrderNumber= Osprey_ReEu.Default_Payment_method("CCMastercard");
             Osprey_ReEu.click_Sales();
			 HashMap<String,String> Orderstatus1 = Osprey_ReEu.Admin_Order_Details(OrderNumber);
//			 Osprey_ReEu.writeOrderNumber1(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("workato"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode,Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));  
			 
	 
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}
	
	
}
