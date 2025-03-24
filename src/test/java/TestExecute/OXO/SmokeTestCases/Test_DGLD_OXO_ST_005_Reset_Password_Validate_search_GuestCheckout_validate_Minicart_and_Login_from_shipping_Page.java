package TestExecute.OXO.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_005_Reset_Password_Validate_search_GuestCheckout_validate_Minicart_and_Login_from_shipping_Page {


		String datafile = "OXO//GoldOxoTestData.xlsx";
		GoldOxoHyva_PRODHelper Oxo=new GoldOxoHyva_PRODHelper(datafile,"DataSet");
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_Footer_Links() throws Exception {

			try {
				Oxo.verifingHomePage();	
				Oxo.click_singinButton();
				Oxo.Forgot_password("AccountDetails");	
				Oxo.coffee_headerlinks("Coffee & Beverage");
				Oxo.addtocart("addproduct");
				Oxo.search_product("Product");
				Oxo.addtocart("Product");
				Oxo.click_on_product("addproduct");
				Oxo.click_on_Image("Product");
				Oxo.minicart_delete("Product");
				Oxo.minicart_product_close();
				//Oxo.minicart_validation("Product Qunatity");
				//Oxo.minicart_freeshipping();		
				Oxo.minicart_Checkout();	
				Oxo.addDeliveryAddress_Guest("AccountDetails");
				Oxo.select_Shipping_Method("GroundShipping method");
				Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
				Oxo.click_singin_Shippingpage();
				Oxo.Usersignin("AccountDetails");
						

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