package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;

import TestLib.Login;
     
public class TEST_DGLD_DB_US_ST_190_Register_User_Multiple_Items_and_Multi_QTY_checkout_Add_GC_Partial_redeem_WC_Partial_redeem_Discount_and_Cancel_Applied_GC_WC_Discount_and_Place_an_Order_with_CC {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Checkout_Multiple_Items_QTY_checkout_Partial_redeem_WC_Partial_redeem_Discount_and_Cancel_Applied_GC_WC_Discount_and_Place_an_Order_with_C () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			String Price= Drybar.Store_Credit_balance();
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.search_product("Product");
			Drybar.addtocart("Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.discountCode("Discount");
			Drybar.Apply_Store_Credit(Price);
			Drybar.gitCard("GiftCode Partial Redeem");
			Drybar.Cancel_Giftcard();
			Drybar.Cancel_StoreCredit();
			Drybar.Cancel_Discount();
			Drybar.updatePaymentAndSubmitOrder("PaymentDetails");
			
	  		

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
