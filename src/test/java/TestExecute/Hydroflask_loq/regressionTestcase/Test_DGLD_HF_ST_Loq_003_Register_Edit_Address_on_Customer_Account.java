package TestExecute.Hydroflask_loq.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.GoldHydroLoq_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_Loq_003_Register_Edit_Address_on_Customer_Account {
	

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroLoq_Helper Hydro = new GoldHydroLoq_Helper(datafile,"Loqata");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_changeAddressIn_AddressBook () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.Edit_Address("AccountDetails");
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
	  Hydro.acceptPrivacy();

	}

}

