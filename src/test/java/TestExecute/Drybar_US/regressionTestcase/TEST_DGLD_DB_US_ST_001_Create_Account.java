package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_001_Create_Account {
	
	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");
//	GoldDrybarusHelper2 Drybar1 = new GoldDrybarusHelper2(datafile,"Address Book");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_Account () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.ClickCreateAccount();
			Drybar.createaccount_verfication("Invalid details");
			Drybar.create_account("NewAccountDetails");
			//Validate_Change_Address_In_AddressBook-178TC
//			Drybar1.Add_Address("Account");
//			Drybar1.change_Shippingaddress_Addressbook("New ShippingAddress");
//			Drybar1.change_Billingaddress_Addressbook("New BillingAddress");
//			Drybar1.Edit_Delete_Address("Account");

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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
}
