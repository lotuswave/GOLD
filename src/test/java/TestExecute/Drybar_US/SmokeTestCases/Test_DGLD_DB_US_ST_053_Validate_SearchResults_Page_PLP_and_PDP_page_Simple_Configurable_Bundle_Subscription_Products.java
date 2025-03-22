package TestExecute.Drybar_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarus_PRODHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_US_ST_053_Validate_SearchResults_Page_PLP_and_PDP_page_Simple_Configurable_Bundle_Subscription_Products {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarus_PRODHyvaHelper Drybar = new GoldDrybarus_PRODHyvaHelper(datafile,"Search");
	GoldDrybarus_PRODHyvaHelper Drybar1 = new GoldDrybarus_PRODHyvaHelper(datafile,"DataSet");
	GoldDrybarus_PRODHyvaHelper Drybar2 = new GoldDrybarus_PRODHyvaHelper(datafile,"Bundles");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_SearchResults_Page_PLP_and_PDP_page_Simple_Configurable_Bundle_Subscription_Products  () throws Exception {

		try {
		    
			Drybar.Verify_Homepage();
			Drybar2.click_singinButton();
			Drybar2.login_Drybar("AccountDetails");
			Drybar.Invalid_search_product("Invalid_Search");
			Drybar.search_product("Valid_Search");  
			Drybar.Sort_By("SortBy");
			Drybar.Filter();
			Drybar1.HairTools_headerlinks("Hair Tools"); 
			Drybar1.view_PLP_page();
			Drybar1.filter_By("Filters");
		    Drybar1.price_filter_validation();
		    Drybar1.HairTools_headerlinks("Hair Tools"); 
			Drybar1.Simple_PDP("PLP Product");//-
			Drybar1.search_product("Configurable Product");
			Drybar1.Configurable_PDP("Configurable Product");
			Drybar2.search_product("Bundle 1");  
			Drybar2.Addtocart_Bundle("Bundle 1");
			Drybar2.search_product("Subscribe_Product");  
			Drybar2.subcribe_product_Add_to_Cart("Subscribe_Product");
			Drybar2.minicart_Checkout();
			Drybar2.RegaddDeliveryAddress("AccountDetails");
			Drybar2.selectshippingmethod("GroundShipping method");
			Drybar2.updatePaymentAndSubmitOrder("PaymentDetails");
			
		

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
