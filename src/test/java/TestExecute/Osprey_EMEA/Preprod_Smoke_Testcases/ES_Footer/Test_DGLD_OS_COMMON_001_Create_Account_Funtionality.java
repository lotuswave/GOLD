package TestExecute.Osprey_EMEA.Preprod_Smoke_Testcases.ES_Footer;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_HYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_001_Create_Account_Funtionality {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_HYVA Osprey_ReEu = new OspreyEMEA_HYVA(datafile,"CreateAccount");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Create_account_Funtionality () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        Osprey_ReEu.createaccount_exitingemail("Existing details");
        Osprey_ReEu.Create_Account("Create Account");
        Osprey_ReEu.Click_Myorders_and_Account("Edit contactinfo");          
        Osprey_ReEu.Edit_Name("Edit contactinfo");
       // Osprey_ReEu.Account_page_Validation("Account validation");  //covered in My_Account_page_Validation testcase
       // Osprey_ReEu.signout();
       
        
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
		String url = "https://mcloud-na-preprod.osprey.com/es/";
		System.setProperty("url", url);
        Login.signIn();

	}

}
