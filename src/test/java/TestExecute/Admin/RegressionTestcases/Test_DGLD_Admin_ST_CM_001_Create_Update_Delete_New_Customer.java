package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_ST_CM_001_Create_Update_Delete_New_Customer {
	
	
	
	String datafile = "Admin//AdminTestData.xlsx";
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_update_Delete_NewCustomer() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Customers();
			Admin.Allcustomers();
			Admin.Newcustomer("Customer");
			Admin.Customerdetails("Customer");
			 Admin.Updatedetails("Customer");
		     Admin.Address("Address");
		     Admin.Orders("Orders"); 
		     Admin.Returns("Orders");
//		     Admin.shoppingcart("shoppingid");
		     Admin.Whishlist("Whishlist");
		     Admin.Newsletter();
		     Admin.Savecustomer();
		     Admin.Delete_customer("Customer");
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
