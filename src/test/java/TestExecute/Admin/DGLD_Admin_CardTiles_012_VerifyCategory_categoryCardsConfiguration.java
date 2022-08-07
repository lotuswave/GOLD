package TestExecute.Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class DGLD_Admin_CardTiles_012_VerifyCategory_categoryCardsConfiguration {


	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_validateproductcard_configuration() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.Pages();
			Admin.NewpageCTA();
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.Cardtile_content();
			Admin.dragndrop_Cardtile_Content();
			Admin.featurescardconfiguration("ProductcardTile");
			Admin.category_cards_config("ProductcardTile");
			Admin.Configure_padding_marins("ProductcardTile");
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("ProductcardTile");
			Admin.verify_Padding_fronytend("ProductcardTile");
			Admin.verifycategoriesdisplay("ProductcardTile");
			Admin.Contentpage();
			Admin.edit_block_content("ProductcardTile");
			Admin.category_cards_config("OXOproducttile");
			Admin.Editandsavepage();
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("OXOproducttile");
			Admin.verify_Padding_fronytend("ProductcardTile");
			Admin.verifycategoriesdisplay("OXOproducttile");
			Admin.deletepage("ProductcardTile");
			Admin.Clearfilter();


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
