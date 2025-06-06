package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_ST_006_Guest_User_Mutliple_Items_GC_Partial_Redeem_Express_Paypal_Standard_Shipping {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_User_Mutliple_Items_GC_Partial_Redeem_Express_Paypal_Standard_Shipping() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			Thread.sleep(5000);
			String Description ="Guest User Multiple Item + GC Partial redeem + Express PayPal - Standard Shipping";
			Oxo.verifingHomePage();
			Oxo.search_product("SKU-11244200 - 1QTY");
			Oxo.addtocart("SKU-11244200 - 1QTY");
			
			Oxo.search_product("SKU-11261400 - 2QTY");
			Oxo.addtocart("SKU-11261400 - 2QTY");
			Oxo.search_product("SKU-11301800-1QTY");
			Oxo.addtocart("SKU-11301800-1QTY");
			Oxo.minicart_viewcart();
			Oxo.remove_Free_Product_website();
			Oxo.minicart_Checkout();
			 String Used_GiftCode= Oxo.Gift_card("Giftcard");
			 HashMap<String,String> Details=Oxo.ordersummary_Details();
			String OrderNumber= Oxo.ExpressPaypal("PaypalDetails");
			//Oxo.Admin_signin("Login Details");
			//Oxo.click_Sales();
			//HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			//Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"),Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
	         
			Oxo.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));
	         
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
//	}
	
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
