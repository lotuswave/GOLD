package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_060_Validate_RegisterUser_Checkout_VisaCC_Tax_Employee_Discount_with_Same_Billing_and_Shipping {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUser_Checkout_VisaCC_Tax_Employee_Discount_with_Same_Billing_and_Shipping () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("EmployeeAccountDetails");
			Drybar.search_product("Product");  
			Drybar.addtocart("Product");
			Drybar.employee_discount();
			Drybar.minicart_Checkout();
			Drybar.RegaddDeliveryAddress("AccountDetails");
			Drybar.selectshippingmethod("GroundShipping method");
			Drybar.tax_validation_Paymentpage("Address");
			Drybar.clickSubmitbutton_Shippingpage();
			Drybar.discountCode("Discount");
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
