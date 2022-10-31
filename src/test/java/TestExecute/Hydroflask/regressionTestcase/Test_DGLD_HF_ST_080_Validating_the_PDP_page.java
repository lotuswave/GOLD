package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_080_Validating_the_PDP_page {
	String datafile = "Hydroflask\\GoldHydroTestData.xlsx";	
	GoldHydroHelper Hydro = new GoldHydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Account_Information_AlternatePath_User_Changes_Email() throws Exception {

		try {
			
			
		Hydro.verifingHomePage();
		Hydro.bottles_headerlinks("Bottles & Drinkware"); 
	    Hydro.Configurable_addtocart_pdp("Product");
		Hydro.PDP_cofigurable_product();
		Hydro.click_minicartatPDP();
		Hydro.click_Customize();
		Hydro.Sticky_Add_to_Cart("Product");
		Hydro.writeareview("Product");
		Hydro.Recommended_for_you();
		
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
		  Hydro.ClosADD();
	  Hydro.acceptPrivacy();
		  
	  }

	



}
