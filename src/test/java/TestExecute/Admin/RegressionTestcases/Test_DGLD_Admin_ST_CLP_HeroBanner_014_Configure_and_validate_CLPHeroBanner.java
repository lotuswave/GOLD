package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CLP_HeroBanner_014_Configure_and_validate_CLPHeroBanner {


	String datafile = "Admin//GoldAdminTestData.xlsx";

	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "Cardtile,PLP,CLP");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Admin_validate() throws Exception {

	try {
		Admin.Admin_signin("AccountDetails");
		Admin.click_content();
		Admin.pages();
		Admin.newpageCTA("CLPHerobanner");
		Admin.Contentpage();
		Admin.hot_elements();
		Admin.PLPCMS_content();
		Admin.dragndrop_CLP_Hero_Content();
		Admin.edit_CLP_block_content("CLPHerobanner");
		Admin.Configure_textcolor_light();
		Admin.Content_Alignment("left");
		Admin.Configure_content("CLPHerobanner");
		Admin.CMS_ImageElement("CLPHerobanner");
		Admin.Configure_padding_marins("CLPHerobanner");
		Admin.Editandsavepage();
		Admin.savecontent("CLPHerobanner");
		Admin.openwebsite("CLPHerobanner");
		Admin.ClosADD();
		Admin.AcceptAll();
		Admin.validate_text_color("light");
		Admin.validate_content_alignment("left");
		Admin.Validate_CLP_Content_Frontend("CLPHerobanner");
		Admin.verify_Padding_fronytend("CLPHerobanner");
		Admin.Navigate_adminpage();
		Admin.edit_CLP_block_content("CLPHerobanner");
		Admin.Configure_textcolor_dark();
		Admin.Content_Alignment("right");
		Admin.Editandsavepage();
		Admin.savecontent("CLPHerobanner");
		Admin.openwebsite("OXOCLPHerobanner");
		Admin.AcceptAll();
		Admin.validate_text_color("dark");
		Admin.validate_content_alignment("right");
		Admin.Validate_CLP_Content_Frontend("OXOCLPHerobanner");
		Admin.verify_Padding_fronytend("OXOCLPHerobanner");
		Admin.Navigate_adminpage();
		Admin.Clone("CLPHerobanner");
		Admin.openwebsite("OXOCLPHerobanner");
		Admin.verify_clonefunctionality_website("CLPHerobanner");
		Admin.openwebsite("OXOCLPHerobanner");
		Admin.verify_clonefunctionality_website("CLPHerobanner");
	    Admin.Navigate_adminpage();
		Admin.Delete_hotcomponent("CLPHerobanner");
		Admin.delete_existing_page("CLPHerobanner");
		

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
