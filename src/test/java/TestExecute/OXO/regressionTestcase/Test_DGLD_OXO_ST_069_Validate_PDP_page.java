package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_069_Validate_PDP_page {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo = new GoldOxoHelper(datafile,"PDP");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_PDP_page() throws Exception {

		try {
			Oxo.verifingHomePage();

//			Oxo.babytoddler_headerlinks("Baby & Toddler");
			Oxo.search_product("Product");
			Oxo.Configurable_addtocart_pdp("Product");
			Oxo.click_UGC();
//			Oxo.Configurableproduct_addtocart_pdp("Product");  //
			Oxo.PDP_cofigurable_product();
			Oxo.click_minicartatPDP();
			Oxo.configurableproduct_Sticky_add_to_cart("Product");
			Oxo.writeareview("Product");
			Oxo.Recommended_for_you();
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	

	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}
}



