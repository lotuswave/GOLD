package TestExecute.OXO.regressionTestcase;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_074_Register_User_Back_In_stock_Subcription {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"Outofstock");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Back_In_stock_Subcription() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton(); 
			Oxo.Usersignin("AccountDetails");
			Oxo.search_OOSproduct("Outofstock");
			String price = Oxo.reg_outofstock_subcription("Outofstock");
			Oxo.My_order_subcribtion("Outofstock");
			Oxo.remove_outofstock_subcribtion(price);
			
			

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
