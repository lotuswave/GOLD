package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_EU.GoldDrybarEUHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_DGLD_DB_EU_ST_009_Guest_user_checkout_with_Standard_Shipping_Method {

		
		String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
		GoldDrybarEUHelper Drybar_EU = new GoldDrybarEUHelper(datafile,"DataSet");
		
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Guest_user_checkout_with_Standard_Shipping_Method () throws Exception {

			try {
			
				Drybar_EU.Verify_Homepage();
				Drybar_EU.HairTools_headerlinks("Hair Tools"); 
				Drybar_EU.addtocart("PLP Product");
				Drybar_EU.minicart_Checkout();
				Drybar_EU.addDeliveryAddress_Guestuser("Address");
				Drybar_EU.selectshippingmethod("GroundShipping method");
				Drybar_EU.clickSubmitbutton_Shippingpage();
				Drybar_EU.updatePaymentAndSubmitOrder("PaymentDetails");	

			} catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@AfterTest
		public void clearBrowser() {
//			Common.closeAll();
			

		}

		@BeforeTest
		public void startTest() throws Exception {
			System.setProperty("configFile", "Drybar_EU\\config.properties");
	        Login.signIn();
	        Drybar_EU.close_add();
	        

		}

	}

		
	


