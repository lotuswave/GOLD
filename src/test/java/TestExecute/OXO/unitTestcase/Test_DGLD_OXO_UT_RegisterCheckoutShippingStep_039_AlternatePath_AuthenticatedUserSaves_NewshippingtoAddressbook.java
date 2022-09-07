package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_RegisterCheckoutShippingStep_039_AlternatePath_AuthenticatedUserSaves_NewshippingtoAddressbook {
	String datafile = "OXO//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Save_new_address () throws Exception {
		try {
	       
			Oxo.validateCreateAccountpageNavigation();
			Oxo.validateaccountcreationpassword("AccountDetails");
	        Oxo.myaccount_addnewaddress("AccountDetails");
	        Oxo.Search_PLP_product("searchproduct");
			Oxo.minicart_Checkout();
		    Oxo.addshippingAddress("AccountDetails");
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }




}
