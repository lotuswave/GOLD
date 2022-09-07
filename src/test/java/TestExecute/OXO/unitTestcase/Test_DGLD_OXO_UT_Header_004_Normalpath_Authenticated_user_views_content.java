package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Header_004_Normalpath_Authenticated_user_views_content {
	
	String datafile = "oxo//OXOTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void customerLogin() throws Exception {

		try {
	     
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.Toplevelnavigation("headerlinks");
		
	        
			Oxo.MyFavoritespage("Myfavorite");
			Oxo.Myorderspage("Myorders");
			Oxo.MyProductSubscription("Myproduct");
			Oxo.ValidateMyAccountpage("Myaccount");
			Oxo.Signout("signout");
		
	        
	        
	   
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
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }


}
