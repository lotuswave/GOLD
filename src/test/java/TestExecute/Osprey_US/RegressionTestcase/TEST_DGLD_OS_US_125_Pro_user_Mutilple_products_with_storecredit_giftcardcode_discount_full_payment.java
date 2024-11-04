package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OS_US_125_Pro_user_Mutilple_products_with_storecredit_giftcardcode_discount_full_payment {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Pro_user_Mutilple_products_with_storecredit_giftcardcode_discount_full_payment () throws Exception {

		try {
			  Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.click_singinButton();
		        Osprey_ReEu.Login_Account("prouser");
		        String Price= Osprey_ReEu.Store_Credit_balance();
		        Osprey_ReEu.search_product("Product");
		        Osprey_ReEu.addtocart("Product");
		        Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
		        Osprey_ReEu.simple_addtocart("Simple product"); 
		        Osprey_ReEu.minicart_Checkout();
		        Osprey_ReEu.RegaddDeliveryAddress("Account");
		        Osprey_ReEu.selectshippingmethod("ProShippingMethod");                       
		        Osprey_ReEu.clickSubmitbutton_Shippingpage();
		        Osprey_ReEu.discountCode("Discount");
//		        Osprey_ReEu.Gift_card("Partial Giftcard");
		        Osprey_ReEu.Apply_Store_Credit(Price);
		        Osprey_ReEu.giftCardSubmitOrder();
        
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}

}
