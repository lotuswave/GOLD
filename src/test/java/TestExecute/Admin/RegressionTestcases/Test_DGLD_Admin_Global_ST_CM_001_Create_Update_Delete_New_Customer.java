package TestExecute.Admin.RegressionTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.GoldAdminHelper;

public class Test_DGLD_Admin_Global_ST_CM_001_Create_Update_Delete_New_Customer {
	
	
	
	String datafile = "Admin//AdminTestData.xlsx";
	GoldAdminHelper Admin = new GoldAdminHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_update_Delete_NewCustomer() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.Customers();
			Admin.Allcustomers();
			Admin.Newcustomer("HYFCustomer");
			Admin.Customerdetails("HYFCustomer");
			 Admin.Updatedetails("HYFCustomer");
		     Admin.Address("USAddress");
		     Admin.Orders("HYFCustomer"); 
		     Admin.Returns("HYFCustomer");
//		     Admin.shoppingcart("shoppingid");
		     Admin.Whishlist("HYFCustomer");
		     Admin.Newsletter();
		     Admin.Savecustomer();
		     Admin.Delete_customer("HYFCustomer");
		     
		     
		     //HYFCustomer OxoCustomer OSPUSCustomer DRYCustomer OPSUKCustomer USAddress change customer name as per the website for emea change UKAddress
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
