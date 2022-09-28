package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import models.admin.Adminhelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_Admin_CM_002_AlternatePath_Admin_Updates_Customers_Information {
		
		String datafile = "Admin//AdminTestData.xlsx";	
		Adminhelper Admin = new Adminhelper(datafile,"DataSet");
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Admin_Updates_Customers_Information() throws Exception {

			try {
			 Admin.Admin_signin("AccountDetails");
			 Admin.Customers();
			 Admin.Allcustomers();
		     Admin.Updatedetails("Customer");
		     Admin.Address("Address");
		     Admin.Orders("Orders"); 
		     Admin.Returns("Orders");
//		     Admin.shoppingcart("shoppingid");
		     Admin.Whishlist("Whishlist");
		     Admin.Newsletter();
		     Admin.Savecustomer();
		     Admin.Clearfilter();
		     
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
			System.setProperty("configFile", "Admin\\config.properties");
			  Login.signIn();
			 
			  
		  }


}
