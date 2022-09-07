package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Minicart_017_026_030_Validate_MiniCart_Functionality {

	String datafile = "OXO//OxoTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_minicart() throws Exception {

		try {
	    
			Oxo.verifingHomePage();
			Oxo.minicart();
			Oxo.headerlinks("Kitchenware");   
			Oxo.minicart_freeshipping();
			Oxo.minicart_delete("Delete Product");
			Oxo.minicart_update("Quantity");
			Oxo.minicart_click_productname();
			Oxo.minicart_product_close();
			Oxo.minicart_viewcart();
			Oxo.minicart_Checkout();
			
			
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
