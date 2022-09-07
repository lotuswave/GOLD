package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_AccountRegistration_036_ExceptionPaths_AccountRegistrationPage_errormessagesvalidation {
	String datafile = "OXO//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void AccountRegistrationPage_errormessagesvalidation() throws Exception {
		try {
	       
			Oxo.verifingHomePage();
			Oxo.validateCreateAccountpageNavigation();
			Oxo.createNewAccount_Required_Fieldsvalidation();
			Oxo.validate_Createnewaccount_email("Invalid Email");
			Oxo.validate_Createnewaccount_invalidpasssword("invalidpassword");
			Oxo.validate_Createnewaccount_passswordFields("AccountDetails");
			Oxo.validate_Createnewaccount_existingemail("AccountDetails");
			
		
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

