package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_179_RegUser_Multiple_Items_QTY_WC_Fullredeem_and_Cancel_Applied_WC_and_Place_Order_with_PayPal_and_Verify_Order_details_in_MYOrdersPage {

	
	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Reg_User_Checkout_Storecredit_Applied_and_Cancelled_place_with_Paypal() throws Exception {

		try {
			
			
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("AccountDetails");
			String Price= Drybar.Store_Credit_balance();
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.HairTools_headerlinks("Hair Tools"); 
			Drybar.addtocart("PLP Product");
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.Apply_Store_Credit(Price);
			Drybar.Cancel_StoreCredit();
			String Ordernumber=Drybar.payPal_Payment("PaypalDetails");
			Drybar.Order_ID_Verification(Ordernumber);

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
	
}
