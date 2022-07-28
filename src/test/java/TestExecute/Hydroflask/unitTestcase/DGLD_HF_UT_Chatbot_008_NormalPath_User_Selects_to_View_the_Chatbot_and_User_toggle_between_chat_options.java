package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Login;

public class DGLD_HF_UT_Chatbot_008_NormalPath_User_Selects_to_View_the_Chatbot_and_User_toggle_between_chat_options {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_User_selects_chatbot_and_toggle_between_chatbot() throws Exception {

		try {
			Hydro.click_ChatBot("Chatoptions");
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
