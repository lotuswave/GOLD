package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_004_Shopping_CartPage_Validation {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Shopping_cart_Page_Functionality () throws Exception {

		try {
			Drybar.Verify_Homepage();
			Drybar.search_product("Configurable Product");
			Drybar.Configurable_addtocart("Configurable Product");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.click_minicart();
			Drybar.minicart_viewcart();
            Drybar.Remove_Product("Configurable Product");
            Drybar.update_shoppingcart("Product Qunatity");
            Drybar.minicart_Checkout();
            Drybar.addDeliveryAddress_Guestuser("Address");
            Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.Shoppingcart_page();
			//Drybar.minicart_ordersummary_discount("Discount");
            
 
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