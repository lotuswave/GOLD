package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_006_Cart_Page_Validation {

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Shopping_cart_Page_Functionality () throws Exception {

		try {
			Drybar.Verify_Homepage();
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
			Drybar.Remove_Product("Configurable Product");
			Drybar.update_shoppingcart("Product Qunatity");
            Drybar.minicart_Checkout();
            Drybar.addDeliveryAddress_Guestuser("Address");
            Drybar.selectshippingmethod("GroundShipping method");
            Drybar.clickSubmitbutton_Shippingpage();
			Drybar.Shoppingcart_page();
			Drybar.minicart_ordersummary_discount("Discount");
            
 
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
		System.setProperty("configFile", "Drybar_EU\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}