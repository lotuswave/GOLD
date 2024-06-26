package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_174_Change_Address_In_AddressBook {
	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"Address Book");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Change_Address_In_AddressBook () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_Createaccount();
			Drybar.create_account("Create Account");
			Drybar.Add_Address("Account");
			Drybar.change_Shippingaddress_Addressbook("New ShippingAddress");
			Drybar.change_Billingaddress_Addressbook("New BillingAddress");
			Drybar.Edit_Delete_Address("Account");
			

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
