package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestComponent.Drybar_US.GoldDrybarus_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_US_ST_033_Register_user_Checkout_with_Multiple_Items_Aerosal_Non_AerosalRegular_items_Bundle_different_Shipping_Methods_and_validate_CC_and_PayPal {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarus_PRODHyvaHelper Drybar = new GoldDrybarus_PRODHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Checkout_Aerosol_Products_and_NonAerosol_with_Bundle_different_Shipping_Methods_and () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Bundle Product");  
			Drybar.Addtocart_Bundle("Bundle Product");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");// Need to change 2day shipping
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.updatePaymentAndSubmitOrder("CCDiscovercard");
			Drybar.payPal_Payment("PaypalDetails");
			
		
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
//        Drybar.close_add();
        

	}

}
