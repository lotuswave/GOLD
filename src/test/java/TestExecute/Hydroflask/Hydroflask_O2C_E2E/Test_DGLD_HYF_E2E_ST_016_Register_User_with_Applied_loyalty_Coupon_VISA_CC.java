package TestExecute.Hydroflask.Hydroflask_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_E2E_ST_016_Register_User_with_Applied_loyalty_Coupon_VISA_CC {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroE2EHelper Hydro = new GoldHydroE2EHelper(datafile,"E2E");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating__Register_User_with_Applied_loyalty_Coupon_VISA_CC () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Register User with  Applied loyalty  Coupon  - VISA CC";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails_TC16");
			Hydro.search_product("SKU-TT40PS110 -2QTY");     
			Hydro.Configurable_addtocart_pdp("SKU-TT40PS110 -2QTY");
			Hydro.search_product("SKU-TT32PS112 -2QTY");     
			Hydro.Configurable_addtocart_pdp("SKU-TT32PS112 -2QTY");   
			Hydro.search_product("SKU-BO56 -1QTY");     
			Hydro.addtocart("SKU-BO56 -1QTY");
			Hydro.search_product("SKU-P-S21001 - 2QTY"); 
			Hydro.multiline_Engraving("SKU-P-S21001 - 2QTY");
			Hydro.minicart_Checkout();
//			Hydro.RegaddDeliveryAddress("AccountDetails");
			String Used_GiftCode = "NULL";
			Hydro.selectshippingaddress("GroundShipping method");
			Hydro.fivepercent_Reward_Points("$5 Off (100 points)");
		    HashMap<String,String> Details=Hydro.ordersummary_Details();
            String OrderNumber=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
           
			Hydro.writeOrderNumber(Description,OrderNumber,Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"));

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
