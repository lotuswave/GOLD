package TestExecute.OXO_loq.regressionTestcase;

import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoLoq_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_Loq_ST_005_Register_Add_Address_on_Customer_Account {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoLoq_Helper Oxo = new GoldOxoLoq_Helper(datafile,"Loqata");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_changeAddressIn_AddressBook() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			
			Oxo.AddNewAddress_AddressBook("AccountDetails");
			
			
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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();

	}

}

