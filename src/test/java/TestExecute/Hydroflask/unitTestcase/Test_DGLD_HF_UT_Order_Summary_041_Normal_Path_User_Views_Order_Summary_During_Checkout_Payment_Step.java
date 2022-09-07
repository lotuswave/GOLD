package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_Order_Summary_041_Normal_Path_User_Views_Order_Summary_During_Checkout_Payment_Step {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Normal_Path_User_Views_Order_Summary_During_Checkout_Payment_Step() throws Exception {

		try {
			Hydro.verifingHomePage();
//			Hydro.headerlinks("Accessories");       //use in stage
//			Hydro.addtocart("Product");                    //use in stage
			Hydro.search_product_pdp("search");
			Hydro.minicart_Checkout();
			Hydro.addDeliveryAddress("AccountDetails");
			Hydro.verify_ordersummary();
			Hydro.product_verification("Product");
			Hydro.shipping_method_verification("Shipping method");
			
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
		System.setProperty("configFile", "Hydroflask\\config.properties");

		Login.signIn();
		 Hydro.ClosADD();

	}

}
