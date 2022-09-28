package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CM_009_AlternatePath_Admin_UpdateCustomerGroup {

	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Adminupdate_NewCustomerGroup() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Click_customer();
			Admin.Click_customergroups("CustomerGroup");
			Admin.Apply_filter("Createcustomergroup");
			Admin.Click_edit("UpdatecustomerGroup");
			Admin.Update_customergroup("UpdatecustomerGroup");
			Admin.Admin_logout();
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
