package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_053_Validate_PLP_page {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyva_PRODHelper Oxo=new GoldOxoHyva_PRODHelper(datafile,"DataSet");
	GoldOxoHyva_PRODHelper Oxo1=new GoldOxoHyva_PRODHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_PLP_Page() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.view_PLP_page();
			Oxo.sort_By("SortBy");
			Oxo.filter_By("Barware");
			Oxo.color_validation("Sage");
			Oxo.price_filter_validation();
			
			Oxo1.coffee_headerlinks("Coffee & Beverage");
			Oxo1.view_PLP_page();
			Oxo1.sort_By("SortBy");
			Oxo1.filter_By("Barware");
			Oxo1.color_validation("Sage");
			Oxo1.price_filter_validation();

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}

}
