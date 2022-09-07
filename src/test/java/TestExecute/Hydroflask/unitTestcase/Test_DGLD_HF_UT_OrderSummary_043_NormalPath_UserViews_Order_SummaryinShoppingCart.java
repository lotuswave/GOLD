package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_OrderSummary_043_NormalPath_UserViews_Order_SummaryinShoppingCart {

	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void UserViews_Order_SummaryinShoppingCart() throws Exception {

		try {
			
			Hydro.verifingHomePage();
//			Hydro.ClosADD();
			Hydro.search_product1("search");
			Hydro.addtocart("Product"); 
		    Hydro.minicart_Checkout();
		    Hydro.addDeliveryAddress1("AccountDetails");
		    Hydro.Validate_invalid_Discount_code("Discount code");
			Hydro.Validate_valid_Discount_code("Discount code");
			Hydro.verify_ordersummary_valid_discount_code();
		

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
