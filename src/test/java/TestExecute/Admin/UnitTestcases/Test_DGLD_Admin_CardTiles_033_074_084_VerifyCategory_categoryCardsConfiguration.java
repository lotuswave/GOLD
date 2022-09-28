package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CardTiles_033_074_084_VerifyCategory_categoryCardsConfiguration {


	String datafile = "Admin//AdminTestData.xlsx";	
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_validatecategorycard_configuration() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.Pages();
			Admin.NewpageCTA();
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
			Admin.verifycardtileimage_frontend("ProductcardTile");
			Admin.verify_Padding_fronytend("ProductcardTile");
			Admin.verifycategoriesdisplay("ProductcardTile");
			Admin.verifybuttonlink_page("ProductcardTile");
			Admin.Navigate_adminpage();
			Admin.edit_block_content("ProductcardTile");
			Admin.Buttonlink("OXOproducttile");
			Admin.category_cards_config("OXOproducttile");
			Admin.Editandsavepage();
			Admin.savecontent("ProductcardTile");
			Admin.openwebsite("OXOproducttile");
			Admin.verifycardtileimage_frontend("OXOproducttile");
			Admin.verify_Padding_fronytend("OXOproducttile");
			Admin.verifycategoriesdisplay("OXOproducttile");
			Admin.verifybuttonlink_product("OXOproducttile");
			Admin.deletepage("ProductcardTile");
			Admin.Clearfilter();


		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
        Login.signIn();

	}

}
