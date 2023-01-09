package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_030_Header_Links_Validation {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"Header");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.header_kitchenware("Kitchenware");
			Oxo.header_CoffeeAndBeverage("Coffee & Beverage");
			Oxo.header_CleaningAndOrganization("Cleaning & Organization");
			Oxo.header_BabyandToddler("BabyAndToddler");
			Oxo.header_ShopAll("ShopAll");
			Oxo.header_WeAre_Oxo("WeAreOXO");
			Oxo.header_Menu_ImageItemblocksLinks("ShopAll");
			
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
