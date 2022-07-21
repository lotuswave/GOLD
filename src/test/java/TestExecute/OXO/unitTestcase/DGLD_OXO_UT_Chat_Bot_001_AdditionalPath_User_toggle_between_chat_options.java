package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_OXO_UT_Chat_Bot_001_AdditionalPath_User_toggle_between_chat_options {

	String datafile = "Oxo//OxoTestData.xlsx";	
	OxoHelper Oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_toggle_between_chat_options() throws Exception {

		try {
	       
			Oxo.click_ChatBot();
			
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
