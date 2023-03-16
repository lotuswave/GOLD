package TestExecute.Osprey_US.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_001_verifyhomepage_Functionality {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHelper Osprey = new GoldOspreyUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Guest_verifyhomepage_Functionality () throws Exception {

		try {
			Osprey.verifingHomePage();
			
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
