package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_196_Validated_the_Contact_US_in_the_footer_section {

		
		String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
		GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validated_the_Contact_US_in_the_footer_section () throws Exception {

			try {
			
				Drybar.Verify_Homepage();
				Drybar.Contact_US();
				Drybar.Write_to_Us("ContactUs");
				
				
		        
			} catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@AfterTest
		public void clearBrowser() {
			//Common.closeAll();
			

		}

		@BeforeTest
		public void startTest() throws Exception {
			System.setProperty("configFile", "Drybar_US\\config.properties");
	        Login.signIn();
	        Drybar.close_add();
	        

		}
	}
	
	


