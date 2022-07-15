package TestExecute.Admin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class DGLD_Admin_CM_008_AlternatePath_Admin_Creates_NewCustomerGroup {
	
	String datafile = "Admin//AdminTestData.xlsx";	
	Adminhelper Admin=new Adminhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void validate_AdminCreates_NewCustomerGroup() throws Exception {

		try {
		   Admin.Admin_signin("AccountDetails");
	       Admin.Click_customer();
	       Admin.Click_customergroups("CustomerGroup");
	       Admin.Create_newcustomergroup("Createcustomergroup");
	       Admin.Admin_logout();
	       
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {

		  Login.signIn();
		 
		  
	  }
 
	


}
