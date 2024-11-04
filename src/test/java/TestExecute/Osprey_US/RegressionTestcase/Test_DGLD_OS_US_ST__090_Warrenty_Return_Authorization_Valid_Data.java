package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST__090_Warrenty_Return_Authorization_Valid_Data {
	
	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Warrenty");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Warrenty_Return_Authorization_Valid_Data () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
	        Osprey_ReEu.click_singinButton();
	        Osprey_ReEu.Login_Account("Account");
	        Osprey_ReEu.warrenty_return_Authorization();
	        Osprey_ReEu.warrent_Return_Auth_Form("valid Details");  
	         
        
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}