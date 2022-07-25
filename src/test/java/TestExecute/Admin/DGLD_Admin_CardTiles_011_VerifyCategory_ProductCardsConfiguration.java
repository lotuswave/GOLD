package TestExecute.Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class DGLD_Admin_CardTiles_011_VerifyCategory_ProductCardsConfiguration {

	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_validateproductcard_configuration() throws Exception {

		try {
			Admin.Admin_signin("pagetitle");
			Admin.click_content();
			Admin.Pages();
			Admin.NewpageCTA();
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.Cardtile_content();
			Admin.dragndrop_Cardtile_Content();
			Admin.close_Editblock("ProductcardTile");
			Admin.tile_buttontext("ProductcardTile");
			Admin.featurescardconfiguration("ProductcardTile");
			Admin.productcardconfiguration("ProductcardTile");
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("ProductcardTile");
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
