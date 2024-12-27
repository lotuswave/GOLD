package TestExecute.OXO.OXO_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoE2EHelper;
import TestLib.*;

public class Test_DGLD_OXO_E2E_ST_009_Registeruser_checkout_MultipleItems_GiftCode_full_redemption {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoE2EHelper Oxo=new GoldOxoE2EHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registeruser_checkout_MultipleItems_GiftCode_full_redemption() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			String Description ="Register user checkout Multiple Items + Gift Code full redemption";
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("SKU-11244200 - 2QTY");
			Oxo.addtocart("SKU-11244200 - 2QTY");
			Oxo.search_product("SKU-1155901- 3QTY");
			Oxo.Configurable_addtocart_pdp("SKU-1155901- 3QTY");
			Oxo.search_product("SKU-1334480V1");
			Oxo.addtocart("SKU-1334480V1");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			 Oxo.select_Shipping_Method("GroundShipping method");
			 String Used_GiftCode =  Oxo.Gift_card("Full_RedeemGiftcard");
			String  OrderNumber=Oxo.giftCardSubmitOrder();
			Oxo.Admin_signin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderNumber);
			Oxo.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Used_GiftCode);
		

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
