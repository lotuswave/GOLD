package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_DGLD_DB_EU_ST_009_Guest_user_checkout_with_Standard_Shipping_Method {

		
		String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
		GoldDrybarUSHelper Drybar_US= new GoldDrybarUSHelper(datafile,"DataSet");
		
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Guest_user_checkout_with_Standard_Shipping_Method () throws Exception {

			try {
			
				Drybar_US.Verify_Homepage();
				Drybar_US.HairTools_headerlinks("Hair Tools"); 
				Drybar_US.addtocart("PLP Product");
				Drybar_US.minicart_Checkout();
				Drybar_US.addDeliveryAddress_Guestuser("Address");
				Drybar_US.selectshippingmethod("GroundShipping method");
				Drybar_US.clickSubmitbutton_Shippingpage();
				Drybar_US.updatePaymentAndSubmitOrder("PaymentDetails");	

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
	        Drybar_US.close_add();
	        

		}

	}

		
	


