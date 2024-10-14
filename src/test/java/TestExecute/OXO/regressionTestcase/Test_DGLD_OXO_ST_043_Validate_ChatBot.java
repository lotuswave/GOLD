package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_043_Validate_ChatBot {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_chat_options() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.validateChatboxOptions("Chatoptions");
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}


}