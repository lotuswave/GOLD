package TestExecute.Hydroflask.Archive;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_001_Admin_Guest_user_Checkout_Funtionality_Visa_card {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Admin_Guest_Checkout_Funtionality_Visa_card () throws Exception {
		try {
			Hydro.Admin_prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description="Guest_User_Checkout_with_single_product_with_CC";
			Hydro.verifingHomePage();
			Hydro.search_product("Product");      
			Hydro.addtocart("Product");   
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress_Guestuser("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            String Used_GiftCode="Null";
        	HashMap<String,String> Details=Hydro.ordersummary_Details();
        	String OrderNumber=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			Hydro.Admin_signin("Login Details");
            Hydro.click_Sales();
			HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(OrderNumber);
			Hydro.writeOrderNumber(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("warkato"),Used_GiftCode,Details.get("Subtotal"),Details.get("shipping"),Details.get("Tax"),Details.get("Discount"),Details.get("ordertotal"),Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
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
