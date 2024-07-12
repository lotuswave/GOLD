package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_DGLD_DB_EU_ST_002_RegisterUser_Checkout_with_Configurable_and_SimpleProducts_with_Sofort_Payment_Method {

		
	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Reg_user_checkout_with_with_Sofort_Payment_Method  () throws Exception {

			try {
			
				Drybar.Verify_Homepage();
				Drybar.click_singinButton();
				Drybar.login_Drybar("AccountDetails");
				Drybar.HairTools_headerlinks("Hair Tools"); 
				Drybar.addtocart("PLP Product");
				Drybar.minicart_Checkout();
				Drybar.RegaddDeliveryAddress("AccountDetails");
				Drybar.selectshippingmethod("GroundShipping method");
				Drybar.clickSubmitbutton_Shippingpage();
				Drybar.Sofort_payment("Sofort");
				

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
			System.setProperty("configFile", "Drybar_EU\\config.properties");
	        Login.signIn();
	        Drybar.close_add();
	        

		}

	}

		
	


