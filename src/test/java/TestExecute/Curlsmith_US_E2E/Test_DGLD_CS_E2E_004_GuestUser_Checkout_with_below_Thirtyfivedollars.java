package TestExecute.Curlsmith_US_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Curlsmith.CurlsmithE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_CS_E2E_004_GuestUser_Checkout_with_below_Thirtyfivedollars {

	String datafile = "Curlsmith/CurlsmithTestData.xlsx";
	CurlsmithE2EHelper curlsmith = new CurlsmithE2EHelper(datafile, "Dataset");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_GuestUser_Checkout_with_below_fiftydollar() throws Exception {

		try {
			curlsmith.prepareOrdersData("CurlsmithUS_E2E_orderDetails.xlsx");
			String Description = "Guest user checkout with below 50 $";
			curlsmith.admin_Sigin("Admin Account Details");
			curlsmith.online_Store();
			curlsmith.verify_Homepage();
			curlsmith.search_product("Product");
			curlsmith.Configurable_addtocart("Configurable Product");
			curlsmith.minicart_Checkout();
			curlsmith.addDeliveryAddress_Guestuser("Address");
			String Discountcode = "Null";
			curlsmith.select_Shipping_Method();
			String ConfirmationNumber = curlsmith.CC_payment_method("Visa Payment");
			String OrderNumber = curlsmith.search_order(ConfirmationNumber);
			HashMap<String, String> Orderstatus1 = curlsmith.orderverification(OrderNumber);
			curlsmith.writeOrderNumber(Description, OrderNumber, Orderstatus1.get("Skus"),ConfirmationNumber, Orderstatus1.get("CustomerPO"),
					Orderstatus1.get("AdminOrderstatus"), Discountcode);

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
		System.setProperty("configFile", "Curlsmith\\config.properties");
		Login.signIn();

	}

}
