package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_097_RegisterUser_SimpleConfigurable_BundleAerosolProducts_Discount_Storecredit_Partialredeem_GiftCode_PartialRedeem_KlarnaStandard_Shipping {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registered_User_Checkout_Funtionality_Amex_card () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			String Price= Drybar.Store_Credit_balance();
			Drybar.search_product("Bundle Product");  
			Drybar.Addtocart_Bundle("Bundle Product");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.add_aerosolproduct("Aerosol");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.discountCode("Discount");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.gitCard("GiftCode");
			Drybar.Apply_Store_Credit(Price);
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
