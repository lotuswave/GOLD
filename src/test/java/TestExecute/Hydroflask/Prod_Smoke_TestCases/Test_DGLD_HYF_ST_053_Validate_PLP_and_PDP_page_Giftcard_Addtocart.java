package TestExecute.Hydroflask.Prod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_053_Validate_PLP_and_PDP_page_Giftcard_Addtocart {

		String datafile = "Hydroflask//GoldHydroTestData.xlsx";
		GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");
		GoldHydroHyva_PRODHelper Hydro1 = new GoldHydroHyva_PRODHelper(datafile, "PDP");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_PLP_and_PDP_page_Giftcard_Item_Addtocart () throws Exception {

			try {
				Hydro.verifingHomePage();
/*
*  PLP Validation
*/				Hydro.search_product("Product");
				Hydro.bottle_Accessories_headerlinks("Accessories"); 
				Hydro.view_PLP_page();
				Hydro.sort_By("SortBy");
				Hydro.filter_By("Accessories");
				Hydro.color_validation("Black");
				Hydro.price_filter_validation();
		
/*
 * PDP Validation			
 */
				Hydro1.search_product("Product"); 	
				Hydro1.Configurableproduct_addtocart_pdppage("Product");
				Hydro.Gift_cards("Hydro Gift Card");
				Hydro.Card_Value("price");
				Hydro.minicart_Checkout();
						

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
			System.setProperty("configFile", "Hydroflask\\prodconfig.properties");
			Login.signIn();
			Hydro.close_add();
	        Hydro.acceptPrivacy();
		}

	}
