package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_109_RegisterUser_checkout_AfterPay_Payment_Standard_shipping {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_RegisterUser_checkout_AfterPay_Payment_Standard_shipping () throws Exception {

		try {
			Drybar.Verify_Homepage();
        Drybar.click_singinButton();
        Drybar.login_Drybar("AccountDetails");
        Drybar.search_product("Product");
        Drybar.addtocart("Product");
        Drybar.minicart_Checkout();
        Drybar.RegaddDeliveryAddress("AccountDetails");
        Drybar.selectshippingmethod("GroundShipping method");
        Drybar.clickSubmitbutton_Shippingpage();
        Drybar.After_Pay_payment("Afterpay");
        
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


