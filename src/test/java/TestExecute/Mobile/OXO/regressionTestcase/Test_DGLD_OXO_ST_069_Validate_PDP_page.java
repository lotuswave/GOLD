package TestExecute.Mobile.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestComponent.OXO.GoldOxoHelper_Mobile;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_069_Validate_PDP_page {
	String datafile = "OXO//GoldOxoTestData.xlsx";	
	
	GoldOxoHelper_Mobile Oxo = new GoldOxoHelper_Mobile(datafile,"PDP");
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Checkout_Bundle() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_Shop();
			Oxo.click_BabyToddler();
			Oxo.click_FeedingDrinking();
			Oxo.click_product();
			Oxo.Configurableproduct_addtocart_pdp("Product");
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
		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 String device=System.getProperty("dev","IOS");
			System.setProperty("configFile", "oxo/mobile_config.properties");
			if(device.equalsIgnoreCase("ios")) {
				System.setProperty("configFile", "oxo/mobile_config_ios.properties");
			}
			  Login.mobilesignIn(device);
			  Oxo.acceptPrivacy();
		}
}



