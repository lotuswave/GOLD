package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_053_Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase {
String datafile = "OXO//GoldOxoTestData.xlsx";
	
	GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile, "Search");
	GoldOxoHyva_PRODHelper Oxo1=new GoldOxoHyva_PRODHelper(datafile,"DataSet");
	GoldOxoHyva_PRODHelper Oxo2 = new GoldOxoHyva_PRODHelper(datafile,"PDP");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase() throws Exception {

		try {

			Oxo.verifingHomePage();
			Oxo.Invalid_search_product("Invalid_Search");
			Oxo.search_product("Valid_Search");
			Oxo.Filter();
			Oxo1.coffee_headerlinks("Coffee & Beverage");
			Oxo1.view_PLP_page();
			Oxo1.sort_By("SortBy");
			Oxo1.filter_By("Barware");
			Oxo1.color_validation("Sage");
			Oxo1.price_filter_validation();
			Oxo2.search_product("ConfigProduct");
			Oxo2.Configurable_addtocart_pdp("ConfigProduct");
			Oxo2.click_UGC();
			Oxo2.PDP_cofigurable_product();
			Oxo2.configurableproduct_Sticky_add_to_cart("ConfigProduct");
			Oxo2.writeareview("Product");
			//Oxo2.Recommended_for_you();
			Oxo1.Gift_cards("Oxo Gift Card");//
			Oxo1.Card_Value("price");
			Oxo1.minicart_Checkout();
			Oxo1.addDeliveryAddress_Guest("AccountDetails");
			Oxo1.select_Shipping_Method("GroundShipping method");
			Oxo1.updatePaymentAndSubmitOrder("PaymentDetails");

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
		System.setProperty("configFile", "oxo\\config.properties");
		Login.signIn();
		Oxo.acceptPrivacy();
	}

}
