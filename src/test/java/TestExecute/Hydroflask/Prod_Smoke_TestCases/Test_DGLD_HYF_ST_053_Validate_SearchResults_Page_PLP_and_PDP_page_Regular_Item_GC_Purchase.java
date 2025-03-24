package TestExecute.Hydroflask.Prod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_053_Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase {

		String datafile = "Hydroflask//GoldHydroTestData.xlsx";
		GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");
		GoldHydroHyva_PRODHelper Hydro1 = new GoldHydroHyva_PRODHelper(datafile, "PDP");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_SearchResults_Page_PLP_and_PDP_page_Regular_Item_GC_Purchase () throws Exception {

			try {
				Hydro.verifingHomePage();
				Hydro.Validating_search("aaabbcc");
				Hydro.search_results("aaabbcc");
				Hydro.popular_searches();
				//Hydro.carousel();
				Hydro.search_product("Product");
				Hydro.Accessories_headerlinks("Bottles & Drinkware"); 
				Hydro.view_PLP_page();
				Hydro.sort_By("SortBy");
				Hydro.filter_By("Collections");
	//			Hydro.color_validation("Black");
				Hydro.price_filter_validation();
				Hydro1.search_product("Product"); 	
				Hydro1.Configurableproduct_addtocart_pdppage("Product");
				Hydro1.Configurableproduct_addtocart_pdp("Product");
				Hydro1.writeareview("Product");
				Hydro.Gift_cards("Hydro Gift Card");
				Hydro.Card_Value("price");
				Hydro.minicart_Checkout();
				Hydro.addDeliveryAddress_Guestuser("AccountDetails");
				Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
						

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
			System.setProperty("configFile", "Hydroflask\\config.properties");
			Login.signIn();
			Hydro.close_add();
	        Hydro.acceptPrivacy();
		}

	}
