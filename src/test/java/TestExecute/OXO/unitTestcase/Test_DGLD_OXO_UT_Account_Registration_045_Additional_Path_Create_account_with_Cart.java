package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Account_Registration_045_Additional_Path_Create_account_with_Cart {

	String datafile = "oxo//OXOTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Account_Registration_Additional_Path_Create_account_with_Cart() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.search_product("searchproduct");
//			Oxo.headerlinks("Kitchenware");
//			Oxo.addtocart("Product");
			String minicart=Oxo.minicart_items();
			Oxo.validateCreateAccountpageNavigation();
			Oxo.validateaccountcreationpassword("AccountDetails");
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

	}

}
