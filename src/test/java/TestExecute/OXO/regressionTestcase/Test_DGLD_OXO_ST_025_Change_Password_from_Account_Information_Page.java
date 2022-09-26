package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_025_Change_Password_from_Account_Information_Page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"DataSet");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_Account() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_Createaccount();
			String NewDetails = Oxo.create_account("AccountDetails");
			Oxo.edit_Account_info("NewDetails");
			Oxo.changed_password(NewDetails);
			
			
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
