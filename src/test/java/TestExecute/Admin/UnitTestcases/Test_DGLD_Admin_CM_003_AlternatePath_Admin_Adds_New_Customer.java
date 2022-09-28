package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import models.admin.Adminhelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_Admin_CM_003_AlternatePath_Admin_Adds_New_Customer {

	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Admin_Adds_New_Customer() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Customers();
			Admin.Allcustomers();
			Admin.Newcustomer("Customer");
			Admin.Customerdetails("Customer");
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
//	@Parameters("URL")
	public void startTest() throws Exception {
		System.setProperty("configFile", "Admin\\config.properties");
		
		Login.signIn();
//		Login.openwebsite(URL);

	}

}
