package TestExecute.Hydroflask.Preprod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_080_Validating_the_PDP_page {
	String datafile = "Hydroflask\\GoldHydroTestData.xlsx";	
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile, "PDP");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_the_PDP_page () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.search_product("Product"); 	
			Hydro.Configurableproduct_addtocart_pdppage("Product");
			

		
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
//		Common.closeAll();

	}
	
	
	@BeforeTest
	  public void startTest() throws Exception {
		  Login.signIn();
		  Hydro.close_add();
	  Hydro.acceptPrivacy();
		  
	  }

	



}
