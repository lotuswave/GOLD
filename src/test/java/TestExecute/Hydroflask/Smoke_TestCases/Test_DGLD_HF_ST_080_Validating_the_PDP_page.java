package TestExecute.Hydroflask.Smoke_TestCases;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_080_Validating_the_PDP_page {
	String datafile = "Hydroflask\\GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile, "DataSet");

	// GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile, "PDP");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_PDP_page() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Configurable_Product");
			Hydro.Configurableproduct_addtocart_pdppage("Configurable_Product");

			Hydro.ribbon();
			Hydro.search_product("Product_PDP");
			Hydro.addtocart("Product_PDP");
			Common.actionsKeyPress(Keys.ESCAPE);
			Hydro.Locally_PDP();
			Hydro.PDP_Tabs("Tabs");
			Common.actionsKeyPress(Keys.UP);
			Hydro.BNPL();
			Hydro.Related_Products();
//			Hydro.User_GenerTed_Content();

			if (Common.getCurrentURL().contains("https://www.hydroflask.com/")) {
				Hydro.Gift_cards("Hydro Gift Card");
				Hydro.Card_Value("price");
				Hydro.minicart_Checkout();

			}
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
