package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_ST_CM_003__Creates_Update_Delete_CustomerGroup {

	String datafile = "Admin//GoldAdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile, "customer");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_Admin_Creates_Update_Delet_CustomerGroup() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Customers();
			Admin.Click_customergroups("CustomerGroup");
			Admin.Create_newcustomergroup("Createcustomergroup");
			Admin.Apply_filter("Createcustomergroup");
			Admin.Click_edit("UpdatecustomerGroup");
			Admin.Update_customergroup("Createcustomergroup");
			Admin.Apply_filter("DeleteCustomergroup");
			Admin.Delete_Existingcustomergroup("DeleteCustomergroup");

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