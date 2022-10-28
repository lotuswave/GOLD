package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CardTiles_015_Configure_and_Validate_Categorycardtiles {

	

	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Cardtile,PLP,CLP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_validatecategorycard_configuration() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.pages();
			Admin.newpageCTA("ProductcardTile");
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.Cardtile_content();
			Admin.dragndrop_Cardtile_Content();
			Admin.edit_block_content("ProductcardTile");
			Admin.featuredcardconfiguration_image("ProductcardTile");
			Admin.category_cards_config("ProductcardTile");
			Admin.Configure_padding_marins("ProductcardTile");
			Admin.Editandsavepage();
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("ProductcardTile");
			Admin.ClosADD();
			Admin.AcceptAll();
			Admin.verifycardtileimage_frontend("ProductcardTile");
			Admin.verify_Padding_fronytend("ProductcardTile");
			Admin.verifycategoriesdisplay("ProductcardTile");
			Admin.CTA_LinksValidationFontend("ProductcardTile");
			Admin.Navigate_adminpage();
			Admin.edit_block_content("ProductcardTile");
			Admin.CTALink("OXOproducttile");
			Admin.category_cards_config("OXOproducttile");
			Admin.Editandsavepage();
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("OXOproducttile");
			Admin.AcceptAll();
			Admin.verifycardtileimage_frontend("OXOproducttile");
			Admin.verify_Padding_fronytend("OXOproducttile");
			Admin.verifycategoriesdisplay("OXOproducttile");
			Admin.CTA_LinksValidationFontend("OXOproducttile");
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
