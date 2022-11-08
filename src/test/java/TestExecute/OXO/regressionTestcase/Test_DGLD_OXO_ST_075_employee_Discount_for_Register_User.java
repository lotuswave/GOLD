package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_075_employee_Discount_for_Register_User {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Register_User_Checkout_Funtionality_Visa_Card() throws Exception {
		
		

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("EmpAccountDetails");
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
//			Oxo.search_product("Product");
//			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.discountCode("EmployeeDiscount");
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
		

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
//		Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}

}
