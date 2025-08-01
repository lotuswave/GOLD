package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_EMEA_RT_069_Place_Order_Validate_ShippingMethods_for_Non_Pro_or_Employee_subtotal_amount_lessthan_or_equal_to_35_Euros_Standard_Shipping_5 {

		String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
		GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validating_Place_Order_Validate_ShippingMethods_for_Non_Pro_or_Employee_subtotal_amount_lessthan_or_equal_to_35_Euros_Standard_Shipping_5 () throws Exception {

			try {

				Hydro.verifingHomePage();  
				Hydro.bottles_headerlinks("bottles-drinkware"); 
				Hydro.Configurable_addtocart_pdp("Product");
				Hydro.minicart_Checkout();
				Hydro.addDeliveryAddress_Guestuser("AccountDetails");
	            Hydro.selectshippingaddress("Below5 Shipping method");
	            Hydro.verifying_Shipping_Amount("AccountDetails");
				Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
				
				
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
