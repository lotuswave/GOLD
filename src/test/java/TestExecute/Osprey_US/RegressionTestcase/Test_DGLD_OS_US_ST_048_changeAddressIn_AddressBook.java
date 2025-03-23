package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_US_ST_048_changeAddressIn_AddressBook {
	
		String datafile = "Osprey_US//GoldOspreyus.xlsx";
		GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Address Book");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Verifying_changeAddressIn_AddressBook () throws Exception {

			try {
				Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.click_Createaccount();
		        Osprey_ReEu.create_account("Create Account");
		        Osprey_ReEu.Add_Address("Account");
		        Osprey_ReEu.change_Shippingaddress_Addressbook("New ShippingAddress");
		        Osprey_ReEu.change_Billingaddress_Addressbook("New BillingAddress");
		        Osprey_ReEu.Edit_Delete_Address("Account");
	        
	        
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