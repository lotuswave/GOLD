package TestExecute.Hydroflask_EMEA.Preprod_Smoke_TestCases.DE_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_DE_BCT_005_Validating_the_PDP_page {
	
	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile, "PDP");
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
		Common.closeAll();

	}
	
	@BeforeTest
	public void startTest() throws Exception {
		String url="https://mcloud-na-stage4.hydroflask.com/de";
		System.setProperty("url", url);
        Login.signIn();
        Hydro.close_add();
        Hydro.acceptPrivacy();

	}
}
