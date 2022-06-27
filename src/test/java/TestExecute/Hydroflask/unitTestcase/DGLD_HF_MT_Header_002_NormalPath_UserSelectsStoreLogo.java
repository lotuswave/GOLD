package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_MT_Header_002_NormalPath_UserSelectsStoreLogo {
	
		
		String datafile = "Hydroflask//HydroTestData.xlsx";	
		HydroHelper Hydro=new HydroHelper(datafile);
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void customerLogin() throws Exception {

			try {
		        Hydro.click_singinButton();
		        Hydro.clickStoreLogo();
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
