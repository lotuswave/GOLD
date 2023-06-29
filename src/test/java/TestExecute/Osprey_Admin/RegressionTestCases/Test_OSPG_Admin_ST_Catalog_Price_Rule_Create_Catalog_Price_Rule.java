package TestExecute.Osprey_Admin.RegressionTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;
import models.admin.OsperyAdminHelper;

public class Test_OSPG_Admin_ST_Catalog_Price_Rule_Create_Catalog_Price_Rule {

	String datafile = "Osprey_Admin//GoldAdminTestData.xlsx";

	OsperyAdminHelper Osprey_Admin = new OsperyAdminHelper(datafile, "CatalogPricerule");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Sceduled_categoryupdate() throws Exception {

		try {
			
		   Osprey_Admin.Admin_signin("AccountDetails");
		   Osprey_Admin.Admin_marketing();
		   Osprey_Admin.Admin_CatalogPriceRule();
		   Osprey_Admin.deleteexistingcatalogrule("Catalogpricedetails");
		   Osprey_Admin.Admin_Create_New_Rule("Catalogpricedetails");
		   Osprey_Admin.Admin_Select_Category("Categoryselection");
		   Osprey_Admin.open_Website("Osprey_EU");
		   Osprey_Admin.Validate_Catalog_Pricerule_HF_Frontend("Categoryselection");
		   Osprey_Admin.Navigate_Adminpage();
		   Osprey_Admin.deleteexistingcatalogrule("Catalogpricedetails");
		  
		   
		  
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
		System.setProperty("configFile", "Osprey_Admin\\config.properties");
		Login.signIn();

	}

}
