package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_EU.GoldDrybarEUHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_DGLD_DB_EU_ST_007_Reg_user_checkout_with_Giftcard_code_Full_Redeem {

		
		String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
		GoldDrybarEUHelper Drybar_EU = new GoldDrybarEUHelper(datafile,"DataSet");
		
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Reg_user_checkout_with_Giftcard_code_Full_Redeem () throws Exception {

			try {
			
				Drybar_EU.Verify_Homepage();
				Drybar_EU.click_singinButton();
				Drybar_EU.login_Drybar("AccountDetails");
				Drybar_EU.HairTools_headerlinks("Hair Tools"); 
				Drybar_EU.addtocart("PLP Product");
				Drybar_EU.minicart_Checkout();
				Drybar_EU.RegaddDeliveryAddress("AccountDetails");
				Drybar_EU.selectshippingmethod("GroundShipping method");
				Drybar_EU.clickSubmitbutton_Shippingpage();
				Drybar_EU.gitCard("GiftCode Full Redeem");
				Drybar_EU.giftCardSubmitOrder();	

			} catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@AfterTest
		public void clearBrowser() {
		//	Common.closeAll();
			

		}

		@BeforeTest
		public void startTest() throws Exception {
			System.setProperty("configFile", "Drybar_EU\\config.properties");
	        Login.signIn();
	        Drybar_EU.close_add();
	        

		}

	}

		
	


