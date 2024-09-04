package TestExecute.Drybar_US.Drybar_O2C_E2E;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_003_Guest_User_Checkout_Master_Card_2Day_Shippment {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Guest_User_Checkout_Master_Card_2Day_Shippment () throws Exception {

		try {
		Drybar.prepareOrdersData("Drybar_E2E_orderDetails.xlsx");
		String Description ="Guest_User_Checkout_Master_Card_2Day_Shippment";
        Drybar.search_product("Product");
        Drybar.addtocart("Product");
        Drybar.minicart_Checkout();
    	Drybar.addDeliveryAddress_Guestuser("Address");
		Drybar.selectshippingmethod("GroundShipping method");
		String OrderNumber=Drybar.updatePaymentAndSubmitOrder("CCMastercard");
		Drybar.writeOrderNumber(OrderNumber, Description);

		
		
		
        
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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}


