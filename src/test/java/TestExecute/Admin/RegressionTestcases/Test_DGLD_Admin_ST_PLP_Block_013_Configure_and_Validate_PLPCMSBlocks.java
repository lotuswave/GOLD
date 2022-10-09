package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_PLP_Block_013_Configure_and_Validate_PLPCMSBlocks {

	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Cardtile,PLP,CLP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Configure_and_Validate_PLP_CMS_Block() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.pages();
			Admin.newpageCTA("PLPBLOCK");
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.PLPCMS_content();
			Admin.dragndrop_PLPCMS_Content();
			Admin.close_Editblock("PLPBLOCK");
			Admin.edit_block_content("PLPBLOCK");
			Admin.UpdatePLPCMScontent("PLPBLOCK");
			Admin.Configure_Video("PLPBLOCK");
			Admin.Configure_padding_marins("PLPBLOCK");
			Admin.Editandsavepage();
			Admin.savecontent("PLPBLOCK");
			Admin.openwebsite("PLPBLOCK");
			Admin.ClosADD();
			Admin.AcceptAll();
			Admin.valiadtetextcolordark();
			Admin.Frontendverification("PLPBLOCK");
			Admin.verify_Padding_fronytend("PLPBLOCK");
			Admin.openwebsite("OXOPLPBLOCK");
			Admin.AcceptAll();
			Admin.Frontendverification("PLPBLOCK");
			Admin.Navigate_adminpage();
			Admin.edit_block_content("PLPBLOCK");
			Admin.Configure_textcolor_light();
			Admin.CTALink("ProductcardTile");
			Admin.CMS_ImageElement("PLPBLOCK");
			Admin.Editandsavepage();
			Admin.savecontent("PLPBLOCK");
			Admin.openwebsite("PLPBLOCK");
			Admin.valiadtetextcolorlight();
			Admin.Frontendverification("PLPBLOCK");
			Admin.CTA_LinksValidationFontend("ProductcardTile");
			Admin.Navigate_adminpage();
			Admin.edit_block_content("PLPBLOCK");
			Admin.CTALink("OXOproducttile");
			Admin.Editandsavepage();
			Admin.savecontent("PLPBLOCK");
			Admin.openwebsite("OXOPLPBLOCK");
			Admin.Frontendverification("PLPBLOCK");
			Admin.CTA_LinksValidationFontend("OXOproducttile");
			Admin.Navigate_adminpage();
			Admin.Clone("PLPBLOCK");
			Admin.Navigate_adminpage();
			Admin.Delete_hotcomponent("PLPBLOCK");
			Admin.delete_existing_page("PLPBLOCK");

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
