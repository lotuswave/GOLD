package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_PB_059_Verify_Promo_Block_Components_TextColor {
	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verify_Promo_Block_Components_TextColor() throws Exception {
		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_content();
			Admin.pages();
	        Admin.newpageCTA("promocontent");
			Admin.Contentpage();
			Admin.hot_elements();
			Admin.dragndrop_promocontentBlock();
			Admin.editpromocontent();
			Admin.Configure_textcolor_dark();
			Admin.Prmocontent_Content("promocontent");
			Admin.savecontent("promocontent");
			Admin.openwebsite("promocontent");
			Admin.promocontent_text_colour_dark_website_verification();
			Admin.openwebsite("oxopromocontent");
			Admin.promocontent_text_colour_dark_website_verification();
			Admin.Configure_textcolor_light();
			Admin.editpromocontent_color("promocontent");
			Admin.Prmocontent_Content("promocontent");
			Admin.savecontent("promocontent");
			Admin.openwebsite("promocontent");
			Admin.promocontent_text_colour_light_website_verification();
			Admin.openwebsite("oxopromocontent");
		    Admin.promocontent_text_colour_light_website_verification();

			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
     // Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
		Login.signIn();

	}

}
