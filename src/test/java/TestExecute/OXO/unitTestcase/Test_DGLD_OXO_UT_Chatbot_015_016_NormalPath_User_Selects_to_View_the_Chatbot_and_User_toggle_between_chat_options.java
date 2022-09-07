package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Chatbot_015_016_NormalPath_User_Selects_to_View_the_Chatbot_and_User_toggle_between_chat_options {

	String datafile = "Oxo//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_toggle_between_chat_options() throws Exception {

		try {
	       
			Oxo.validateChatboxOptions("Chatoptions");
			
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
	
	
	
	// @BeforeTest
	// @Parameters("URL")
	 //   public void startTest(String URL) throws Exception {
		// System.setProperty("configFile", "Oxo\\config.properties");
			//Login.signIn();
	         // Login.openwebsite(URL);

			@BeforeTest
			  public void startTest() throws Exception {
				System.setProperty("configFile", "Oxo\\config.properties");
				  Login.signIn();

	      }



}
