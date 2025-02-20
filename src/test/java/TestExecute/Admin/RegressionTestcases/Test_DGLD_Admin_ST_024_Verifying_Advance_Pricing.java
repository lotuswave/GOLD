package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_024_Verifying_Advance_Pricing {

	String datafile = "Admin\\GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "CatalogPricerule");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Catalog_product_update() throws Exception {
		try {

			Admin.Admin_signin("AccountDetails");
			Admin.Click_Catalog();
			Admin.Click_Products_Catalogmenu();
			Admin.Search_products("AccountDetails");
			Admin.Click_SearchProduct();
			Admin.Click_Edit();
			Admin.QAtest_Advanced_Pricing("AdvancedPricing");
			Admin.Quantityincrease("Quantity");
			Admin.stockstatus("Stockstatus");
			Admin.Website_Selection("Hydroflask");         //OSP_EMEA,OXO,Hydroflask,OSP_US,DRY_US
			Admin.Flush_Magneto_cache("FlushMagento");
			// Front end validation
			Admin.open_website("Hydroflask"); // change to "prodURL" inside this method if executed in prod & Testdata
			Admin.Homepage_searchproduct("Hydroflask");
			Admin.clickPLP_product("Hydroflask");

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
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}
}
