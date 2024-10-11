package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_006_Account_Registration_Create_Account_With_Cart {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Registration_Create_Account_With_Cart() throws Exception {

		try {
			Oxo.verifingHomePage(); // cart+AC creation+guestMyfavourites
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
//			Oxo.minicart_Checkout();
			Oxo.MyFavorites_Guestuser("Product");
			String minicart = Oxo.minicart_items();
			Oxo.click_Createaccount();
			Oxo.create_account("New Account Details");
			Oxo.minicart_products(minicart);
			
			
	

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

