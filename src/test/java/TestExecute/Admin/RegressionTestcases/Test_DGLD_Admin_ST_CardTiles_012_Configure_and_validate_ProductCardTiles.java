package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CardTiles_012_Configure_and_validate_ProductCardTiles {


	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Cardtile,PLP,CLP");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Configure_and_validate_ProductcardTiles() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.pages();
			Admin.newpageCTA("ProductcardTile");
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.Cardtile_content();
			Admin.dragndrop_Cardtile_Content();
			Admin.close_Editblock("ProductcardTile");
			Admin.tile_buttontext("ProductcardTile");
			Admin.featurescardconfiguration_Video("ProductcardTile");
			Admin.productcardconfiguration("ProductcardTile");
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("ProductcardTile");
			Admin.ClosADD();
			Admin.AcceptAll();
			Admin.Websiteverification_productcard("ProductcardTile");
			Admin.openwebsite("OXOproducttile");
			Admin.Websiteverification_productcard("OXOproducttile");
			Admin.Navigate_adminpage();
			Admin.Clone("ProductcardTile");
			Admin.Navigate_adminpage();
			Admin.Hide_functinality();
			Admin.Navigate_adminpage();
			Admin.Delete_hotcomponent("ProductcardTile");

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
