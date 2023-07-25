package TestExecute.Osprey_RMA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.GoldOspreyRMAHelper;
import TestLib.Common;
//import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Login;

public class Test_OSPG_RMA_FT_001_Admin_RMA_Creation {
	String datafile = "Osprey_RMA//GoldRmaTestData.xlsx";
	GoldOspreyRMAHelper OspreyRMA = new GoldOspreyRMAHelper(datafile,"Dataset");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RMA_Creation () throws Exception {

		try {
			OspreyRMA.verifingHomePage();
			OspreyRMA.Create_Account("Create Account");
			OspreyRMA.search_product("Product");
			OspreyRMA.addtocart("Product");
			OspreyRMA.minicart_Checkout();
			OspreyRMA.addDeliveryAddress("Account");
			OspreyRMA.selectshippingmethod("GroundShipping method");
			OspreyRMA.clickSubmitbutton_Shippingpage();
			OspreyRMA.updatePaymentAndSubmitOrder("CCMastercard");
		    String order=OspreyRMA.Verify_order();   
			OspreyRMA.Admin_signin("AccountDetails");
			OspreyRMA.click_sales();
			OspreyRMA.click_Orders_Salesmenu();
			OspreyRMA.enter_orderNumber(order);
	        OspreyRMA.Click_Invoice();
			OspreyRMA.Invoice_Payment("AccountDetails");
			OspreyRMA.Click_Ship();
			OspreyRMA.Ship_Payment("AccountDetails");
			OspreyRMA.RMA();
			OspreyRMA.NEWRMA("ProductNames");
			OspreyRMA.Click_Save();		
			OspreyRMA.My_Account();
			OspreyRMA.click_Spares_Repairs_Reservoirs();
			OspreyRMA.View_Details();
			
			
			
			
			
        
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}


