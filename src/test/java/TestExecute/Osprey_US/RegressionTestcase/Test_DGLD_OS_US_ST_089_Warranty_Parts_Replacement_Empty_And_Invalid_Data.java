package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST_089_Warranty_Parts_Replacement_Empty_And_Invalid_Data{
	
		String datafile = "Osprey_US//GoldOspreyus.xlsx";
		GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Warrenty");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Warrenty_Return_Authorization_InValid_Data () throws Exception {
			
			try {
				Osprey_ReEu.verifingHomePage();
		        	Osprey_ReEu.click_singinButton();
		       		Osprey_ReEu.Login_Account("Account");
		        	Osprey_ReEu.warrenty_return();
		       		Osprey_ReEu.Empty_Details_warrenty_return1("Invalid Details");  
	        
	        
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