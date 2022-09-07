package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Address_Book_062_AlternatePath_User_Changes_Default_Billing_Address {
	
		String datafile = "Hydroflask//HydroTestData.xlsx";
		HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Alternate_Path_User_Changes_Default_BillingAddress() throws Exception {

			try {
			
				Hydro.verifingHomePage();
				Hydro.click_singinButton();
				Hydro.login_Hydroflask("AccountDetails");
		        Hydro.click_MyAccount();
		        Hydro.change_Billingaddress_Addressbook("New BillingAddress");
		     
		     
				
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
			System.setProperty("configFile", "Hydroflask\\config.properties");
	        Login.signIn();
	        Hydro.ClosADD();

		}

	}



