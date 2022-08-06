package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_UT_CheckoutShippingStep_013_AlternatePath_UnauthenticatedUserEntersEmailAssociatedWithAnAccount {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void UnauthenticatedUserEntersEmailAssociatedWithAnAccount () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.headerlinks("Accessories");
			Hydro.addtocart();
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();
	       Hydro.validate_ExistingUser_Login_Checkoutpage("AccountDetails");
	       Hydro.Validate_Signin_Checkoutpage();
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

		  Login.signIn();
		 
		  
	  }


}
