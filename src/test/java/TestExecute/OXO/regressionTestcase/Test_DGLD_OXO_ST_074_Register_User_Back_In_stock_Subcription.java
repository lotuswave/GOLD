package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_074_Register_User_Back_In_stock_Subcription {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"Outofstock");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Back_In_stock_Subcription() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_product("Outofstock");
			Oxo.reg_outofstock_subcription("Outofstock");
			
			
			

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
