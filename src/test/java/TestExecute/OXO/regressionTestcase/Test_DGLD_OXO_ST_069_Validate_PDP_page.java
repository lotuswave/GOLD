package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_069_Validate_PDP_page {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo = new GoldOxoHyvaHelper(datafile,"PDP");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_PDP_page() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.babytoddler_headerlinks("Baby & Toddler");
			Oxo.Configurable_addtocart_pdp("ConfigProduct");
			Oxo.click_UGC();
			Oxo.PDP_cofigurable_product();
			Oxo.configurableproduct_Sticky_add_to_cart("ConfigProduct");
			Oxo.writeareview("Product");
			Oxo.Relatedproducts();
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



