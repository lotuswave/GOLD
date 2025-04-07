package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_ST_003_Guest_User_Checkout_3different_items_standard_shipment {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Checkout_3different_items_standard_shipment() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			String Description ="Guest user checkout with CC- 3 different items  each qty - 3 (Standard) ";
			Oxo.verifingHomePage();
			Oxo.search_product("SKU-12171000 - 3QTY");
			Oxo.addtocart("SKU-12171000 - 3QTY");
			Oxo.search_product("SKU-11320400 - 3QTY");
			Oxo.addtocart("SKU-11320400 - 3QTY");
			Oxo.search_product("SKU-11219400 - 3QTY");
			Oxo.addtocart("SKU-11219400 - 3QTY");
			Oxo.minicart_viewcart();
			Oxo.remove_Free_Product_website();
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_Guest("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			HashMap<String,String> Details=Oxo.ordersummary_Details();
			String Used_GiftCode = "NULL";
			Oxo.clickSubmitbutton_Shippingpage();
			String OrderNumber=Oxo.updatePaymentAndSubmitOrder("CCMastercard"); 
			//Oxo.Admin_signin("Login Details");
			//Oxo.click_Sales();
			//HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			//Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"),Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
			Oxo.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));
	         
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
