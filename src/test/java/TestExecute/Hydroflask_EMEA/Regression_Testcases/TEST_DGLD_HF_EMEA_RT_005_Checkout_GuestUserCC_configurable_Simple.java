package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_005_Checkout_GuestUserCC_configurable_Simple {

		String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
		GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validating_Checkout_GuestUserCC_configurable_Simple () throws Exception {

			try {

				Hydro.verifingHomePage();
				Hydro.search_product("Product");      
				Hydro.addtocart("Product");  
				Hydro.bottles_headerlinks("bottles-drinkware"); 
				Hydro.Configurable_addtocart_pdp("Product");
				Hydro.minicart_Checkout();
				Hydro.addDeliveryAddress_Guestuser("AccountDetails");
	            Hydro.selectshippingaddress("GroundShipping method");
	            Hydro.clickSubmitbutton_Shippingpage();
				Hydro.updatePaymentAndSubmitOrder("CCMastercard");
				
				
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
			System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
			Login.signIn();
			Hydro.close_add();
	        Hydro.acceptPrivacy();
		}

	}
