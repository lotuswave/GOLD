package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_CheckoutShippingStep_028_AdditionalPath_UnauthenticatedUserEntersEmailAssociatedWithAnAccount_ForgotPassword {
	String datafile = "Oxo//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Email_Checkout_with_Forgotpassword () throws Exception {

		try {
	       
		Oxo.verifingHomePage();
		Oxo.headerlinks("Kitchenware");  
	    Oxo.addtocart("Product");
		Oxo.minicart_viewcart();
		Oxo.minicart_Checkout();
		Oxo.validate_ExistingUser_Login_Checkoutpage("invalidpassword");
		Oxo.Validate_invalid_Signin_Checkoutpage();
		Oxo.Click_forgotpasswordLink_Checkout();
			
			
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


