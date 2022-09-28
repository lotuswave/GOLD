package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import models.admin.Adminhelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_Admin_CM_001_NormalPath_Admin_Views_Customer_Grid {
	
		
		String datafile = "Admin//AdminTestData.xlsx";	
		Adminhelper Admin = new Adminhelper(datafile,"DataSet");
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Admin_Views_Customer_Grid() throws Exception {

			try {
			  Admin.Admin_signin("AccountDetails");
		      Admin.Customers();
		      Admin.NewcustomerCTA();
		      Admin.Search_by_keyword();
		      Admin.ActionCTA();
		      Admin.FiltersCTA();
		      Admin.ColumnsCTA();
		      Admin.DefaultView();
		      Admin.PaginationCTA();
		      Admin.EditCTA();
		      
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
