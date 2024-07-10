package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_011_Configurable_Product_Checkout {
	

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	//GoldDrybarEUHelper Drybar_EU = new GoldDrybarEUHelper(datafile,"DataSet");
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Configurable_Product_Checkout () throws Exception {

		try {
		
			
			Drybar.Verify_Homepage();
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.minicart_Checkout();
			Drybar.addDeliveryAddress_Guestuser("Address");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();
		

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Drybar_EU\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
