package TestExecute.API;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.GOLD_API.GoldApi;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_Osp_001_Guest_user_checkout_with_1_Line_Item_QTY2_with_CC {

	String datafile = "API//GoldApiTestData.xlsx";
	GoldApi API = new GoldApi(datafile,"Osprey_US");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_user_checkout_with_1_Line_Item_QTY2_with_CC () throws Exception {

		try {
			
			API.verifingHomePage();	
			API.search_product("Product");
			API.addtocart("Product");
	        API.minicart_Checkout();
	        API.addDeliveryAddress_Guestuser("Account");
	        API.selectshippingmethod("GroundShipping method");
	        API.clickSubmitbutton_Shippingpage();
	        String order= API.updatePaymentAndSubmitOrder("CCVisacard");
	        System.out.println(order);
			API.Admin_signin("AccountDetails");
			API.click_Sales();
			API.Click_Orders_Salesmenu();
			API.Orders(order);
			String id=API.getorder_id("sales order");
			API.Login_Account("Post_Account");
			API.WorkSpace();
			API.select_Website("Website_selection");
			API.Generate_Token("Api_Key");
			

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
		System.setProperty("configFile", "Gold_API\\config.properties");
        Login.signIn();


	}

}
