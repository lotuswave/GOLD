package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST_059_Contactus_form {
		String datafile = "Osprey_US//GoldOspreyus.xlsx";
		GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Forms");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Contactus_form () throws Exception {

			try {
				Osprey_ReEu.verifingHomePage();
		        	Osprey_ReEu.clickContact();
		       		Osprey_ReEu.contactUsPage("contactusEmail");
	        
	        
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