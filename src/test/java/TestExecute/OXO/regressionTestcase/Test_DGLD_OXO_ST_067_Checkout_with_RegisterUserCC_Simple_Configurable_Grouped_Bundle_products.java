package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_ST_067_Checkout_with_RegisterUserCC_Simple_Configurable_Grouped_Bundle_products {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RegisterUserCC_Simple_Configurable_Grouped_Bundle_products() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.coffee_headerlinks("Coffee & Beverage");
			Oxo.addtocart("addproduct");
			Oxo.babytoddler_headerlinks("Baby & Toddler");
			Oxo.Configurable_addtocart_pdp("ConfigProduct");
			Oxo.search_product("Bundle");
			Oxo.addtocart("Bundle");
//			Oxo.search_product("Group");
//			Oxo.addtocart("Group");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			Oxo.updatePaymentAndSubmitOrder("PaymentDetails");
			
			
			

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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();
	}

}
