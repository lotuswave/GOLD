package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_ST_005_RegisterUser_Klarna_2_Items_Standard_Shipping_with_PO_Box_Address {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Klarna_2_Items_Standard_Shipping_with_PO_Box_Address() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			String Description ="RegisterUser_Klarna_2_Items_Standard_Shipping";
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("SKU-11244200 - 4QTY");
			Oxo.addtocart("SKU-11244200 - 4QTY");
			Oxo.search_product("SKU-1155901- 4QTY");
			Oxo.Configurable_addtocart_pdp("SKU-1155901- 4QTY");
			Oxo.minicart_viewcart();
			Oxo.remove_Free_Product_website();
			Oxo.minicart_Checkout();
//			String Products_details=Oxo.shipping_order_details();
//			HashMap<String,String> Shipping=Oxo.Shipingdetails("AccountDetails");
			Oxo.addDeliveryAddress_registerUser("PO box Address");
			 String Used_GiftCode = "NULL";
			 Oxo.select_Shipping_Method("GroundShipping method");
			 HashMap<String,String> Details=Oxo.ordersummary_Details();
//			HashMap<String,String> data=Oxo.OrderSummaryValidation();
			String OrderNumber= Oxo.Kalrna_Payment("Klarna Visa Payment");
//			String OrderIdNumber= Oxo.Verify_order_page();
//			System.out.println(OrderIdNumber); 
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
