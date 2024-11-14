package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_091_Warranty_Parts_Replacement_Valid_Data {
		String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
		OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"Warrenty");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Warrenty_Return_Authorization_InValid_Data () throws Exception {

			try {
				Osprey_ReEu.verifingHomePage();
		        	Osprey_ReEu.click_singinButton();
		       		Osprey_ReEu.Login_Account("Account");
		        	Osprey_ReEu.warrenty_return();
		        	Osprey_ReEu.warrenty_Return_Form("valid Details"); 
		       		
	        
	        
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
			System.setProperty("configFile", "Osprey_emea\\config.properties");
	        Login.signIn();
	        
		}
	}
