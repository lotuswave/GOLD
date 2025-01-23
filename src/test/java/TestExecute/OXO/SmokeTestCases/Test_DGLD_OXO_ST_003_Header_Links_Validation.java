package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_003_Header_Links_Validation {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyva_PRODHelper Oxo = new GoldOxoHyva_PRODHelper(datafile,"Header");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Header_Links() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.header_kitchenware("Kitchenware");
			Oxo.header_CoffeeAndBeverage("Coffee & Beverage");
			Oxo.header_CleaningAndOrganization("Cleaning & Organization");
			Oxo.header_BabyandToddler("BabyAndToddler");
			Oxo.header_CampandGrill("CampandGrill");
			Oxo.header_ShopAll("ShopAll");
			Oxo.header_WeAre_Oxo("WeAreOXO");
			Oxo.header_1_Percent_Planet();
			Oxo.header_Menu_ImageItemblocksLinks("ShopAll");	
			Oxo.header_Shop("Shop");
			
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
