package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_195_RegUser_Simple_AerosolBundle_Configurable_AerosolProduct_NonAerosolBundle_Checkout_Discount_GiftCode_FullRedeem {

	
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegUser_Simple_AerosolBundle_Configurable_AerosolProduct_NonAerosolBundle_Checkout_Discount_GiftCode_FullRedeem () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.add_aerosolproduct("Aerosol");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.search_product("Bundle 1");  
			Drybar.Addtocart_Bundle("Bundle 1");
			Drybar.search_product("Bundle Product");  
			Drybar.Addtocart_Bundle("Bundle Product");
		    Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.discountCode("Discount");
			Drybar.gitCard("GiftCode Full Redeem");
			Drybar.giftCardSubmitOrder();

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
