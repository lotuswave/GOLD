package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_SUB_ST_085_Registeruser_Checkout_MasterCC_Simple_Configurable_AerosolBundle_Add_SameSKU_fromBundle_Discount_Giftcode_partialredeem {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Registeruser_Checkout_MasterCC_Simple_Configurable_AerosolBundle_Add_SameSKU_fromBundle_Discount_Giftcode_partialredeem  () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.search_product("AerosolBundle");  
			Drybar.Addtocart_Bundle("AerosolBundle");
			Drybar.search_product("Aerosol");  
			Drybar.addtocart("Aerosol");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Full_Keg_SUB_Product");  
			Drybar.subcribe_product_Add_to_Cart("Full_Keg_SUB_Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.discountCode("Discount");
			Drybar.gitCard("GiftCode");
			Drybar.updatePaymentAndSubmitOrder("CCMastercard");
			Drybar.subcription_Profile();

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
