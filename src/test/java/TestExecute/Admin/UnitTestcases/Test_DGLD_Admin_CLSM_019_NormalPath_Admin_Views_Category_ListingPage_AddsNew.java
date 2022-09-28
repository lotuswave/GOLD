package  TestExecute.Admin.UnitTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestLib.Common;
import TestLib.Login;
import models.admin.Adminhelper;

public class Test_DGLD_Admin_CLSM_019_NormalPath_Admin_Views_Category_ListingPage_AddsNew {


	String datafile = "Admin//AdminTestData.xlsx";	
	Adminhelper Admin = new Adminhelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void _Admin_NormalPath_Admin_Views_Category_ListingPage_AddsNew() throws Exception {

		try {
			Admin.Admin_signin("AccountDetails");
			Admin.click_catlog();
			Admin.click_categories();
			Admin.Verify_CTALinks();
			Admin.categorylist();
			Admin.category_content();
			Admin.Validate_Content();
			Admin.click_Display();
			Admin.click_search_engine();			
		    Admin.products_category();
			Admin.Click_design();						
	     	Admin.click_Add_Root_Category();	   
	    	Admin.savecategory("Categorydetails");
	    	Admin.deletecategory();			      
		
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



