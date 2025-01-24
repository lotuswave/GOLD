package TestExecute.Hydroflask.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_033_Register_user_Checkout_with_MultipleItems_MyHydro_Product_Bundle_Different_Shipping_Methods_and_validate_CC_PayPal {

String datafile = "Hydroflask//GoldHydroTestData.xlsx";
GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
public void Validating_Register_user_Checkout_MultipleItems_MyHydro_Product_Bundle () throws Exception {
	
	    
	try {
		Hydro.verifingHomePage();
		Hydro.click_singinButton();
		Hydro.login_Hydroflask("AccountDetails");
		Hydro.search_product("Myhydro Product");   
		Hydro.Add_Myhydro("Myhydro Product");  
		Hydro.search_product("Product");       
		Hydro.addtocart("Product"); 

		
		Hydro.minicart_Checkout();
		Hydro.RegaddDeliveryAddress("AccountDetails");
		 Hydro.selectshippingaddress("2 Day method");
 
		Hydro.updatePaymentAndSubmitOrder("PaymentDetails");

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
	System.setProperty("configFile", "Hydroflask\\config.properties");
    Login.signIn();
    Hydro.close_add();
    Hydro.acceptPrivacy();
}
}
