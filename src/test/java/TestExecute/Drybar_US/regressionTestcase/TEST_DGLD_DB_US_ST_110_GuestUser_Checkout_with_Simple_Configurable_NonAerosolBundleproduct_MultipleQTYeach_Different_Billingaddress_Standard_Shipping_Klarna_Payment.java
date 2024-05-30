package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_110_GuestUser_Checkout_with_Simple_Configurable_NonAerosolBundleproduct_MultipleQTYeach_Different_Billingaddress_Standard_Shipping_Klarna_Payment {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Checkout_Funtionality_Discount_Klarna () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.search_product("Bundle Product");  
			Drybar.Addtocart_Bundle("Bundle Product");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.minicart_Checkout();
			Drybar.addDeliveryAddress_Guestuser("Address");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.guest_BillingAddress("BillingDetails");
			Drybar.Kalrna_Payment("Klarna Visa Payment");
			

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
}
