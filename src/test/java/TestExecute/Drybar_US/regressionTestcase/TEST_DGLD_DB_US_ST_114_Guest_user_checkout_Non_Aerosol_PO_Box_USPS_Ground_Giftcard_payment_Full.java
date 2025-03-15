package TestExecute.Drybar_US.regressionTestcase;
     
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
	import TestLib.Login;

	public class TEST_DGLD_DB_US_ST_114_Guest_user_checkout_Non_Aerosol_PO_Box_USPS_Ground_Giftcard_payment_Full {

		String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
		GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_Guest_user_checkout_Non_Aerosol_PO_Box_USPS_Ground_Giftcard_payment_Ful () throws Exception {

			try {
			
				Drybar.Verify_Homepage();
				Drybar.HairTools_headerlinks("Hair Tools"); 
				Drybar.addtocart("PLP Product");
				Drybar.minicart_Checkout();
				Drybar.addDeliveryAddress_Guestuser("PO Box Address");
				Drybar.selectshippingmethod("POBox Shipping method");
				Drybar.clickSubmitbutton_Shippingpage();
				Drybar.gitCard("GiftCode Full Redeem");
				Drybar.giftCardSubmitOrder();
			

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
			System.setProperty("configFile", "Drybar_US\\config.properties");
	        Login.signIn();
	       Drybar.close_add();
	        

		}

}
