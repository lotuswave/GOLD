package TestExecute.Osprey_EMEA.E2E_OrderPlacements;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_E2EHelper;
import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_EMEA_E2E_009_Guest_Regular_item_giftcard_product_ordertotal_cover_with_creditcard_StandardShipping {

	String datafile = "Osprey_EMEA//OSPEMEA_E2E_orderDetails.xlsx";
	OspreyEMEA_E2EHelper Osprey_ReEu = new OspreyEMEA_E2EHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_Regular_item_giftcard_product_ordertotal_cover_with_creditcard_StandardShipping () throws Exception {

		try {
			
			Osprey_ReEu.prepareOrdersData("OspreyEU_E2E_orderDetails.xlsx");
			String Description = "UK store -1 REG + 1 GC PURCHASE + CC";
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.search_product("Product");
			Osprey_ReEu.simple_addtocart("Product");
			Osprey_ReEu.minicart_Checkout();
			String Emailid=	Osprey_ReEu.addDeliveryAddress_Guestuser("Account");
			Osprey_ReEu.selectshippingmethod("GroundShipping method");
			Osprey_ReEu.clickSubmitbutton_Shippingpage();
			String OrderNumber = Osprey_ReEu.updatePaymentAndSubmitOrder("CCDiscovercard");
			Osprey_ReEu.writeOrderNumber(OrderNumber, Description, Emailid);
        
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
