package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DGLD_HF_UT_010_Validate_MiniCart_Functionality {


	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void All_Minicart_Scenarios() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.headerlinks("Accessories");
			Hydro.minicart_freeshipping();    
			Hydro.minicart_delete("delete product");
			Hydro.minicart_update("Quantity");
			Hydro.minicart_click_productname();
			Hydro.minicart_product_close();
			Hydro.minicart_viewcart();
			Hydro.minicart_Checkout();
			
	        
	       
	       
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
