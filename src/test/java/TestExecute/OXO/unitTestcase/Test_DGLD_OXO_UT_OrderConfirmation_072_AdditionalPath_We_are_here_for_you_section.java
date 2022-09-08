package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_OrderConfirmation_072_AdditionalPath_We_are_here_for_you_section {
	String datafile = "OXO//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Alternate_Path_User_Changes_Default_ShippingAddress() throws Exception {
		try {
	       
			Oxo.verifingHomePage();
			Oxo.validateCreateAccountpageNavigation();
			Oxo.validateaccountcreationpassword("AccountDetails");
			Oxo.Search_PLP_product("searchproduct");
		
			Oxo.minicart_Checkout();
			Oxo.populate_Shippingaddress_fields("AccountDetails");
			Oxo.Validate_Paymentpage();
			Oxo.addPaymentDetails("PaymentDetails");
			Oxo.Validate_OrderConfirmationPage("SuccessPage");
			Oxo.Validating_We_are_here_for_you_section_Content("SuccessPage");
		    Oxo.Validating_We_are_here_for_you_section_hyperlink("SuccessPage");
	
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
		  Oxo.AcceptAll();
		  
	  }



}


