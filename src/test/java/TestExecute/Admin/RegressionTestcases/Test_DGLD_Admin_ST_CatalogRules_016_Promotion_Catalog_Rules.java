package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CatalogRules_016_Promotion_Catalog_Rules {

	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "CatalogPricerule");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verify_Catalog_Price_rule() throws Exception {
		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Admin_marketing();
			Admin.Admin_CatalogPriceRule();
			Admin.deleteexistingcatalogrule("Catalogpricedetails");
			Admin.Admin_Create_New_Rule("Catalogpricedetails");
			Admin.Admin_Select_Category("Categoryselection");
			Admin.open_website("Hydroflask");
			Admin.Validate_Catalog_Pricerule_HF_Frontend("Categoryselection");
			Admin.open_website("OXO");
			Admin.Validate_Catalog_Pricerule_OXO_Frontend("Categoryselection");
			Admin.Navigate_Adminpage();
			Admin.deleteexistingcatalogrule("Catalogpricedetails");
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
