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
GoldHydroHyvaHelper Hydro1 = new GoldHydroHyvaHelper(datafile,"Bundle");
GoldHydroHyvaHelper Hydro2 = new GoldHydroHyvaHelper(datafile,"MyHydro");

@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
public void Validating_Register_user_Checkout_MultipleItems_MyHydro_Product_Bundle () throws Exception {
	
	    
	try {
		Hydro.verifingHomePage();
		Hydro.click_singinButton();
		Hydro.login_Hydroflask("AccountDetails"); 
		Hydro2.search_product("Myhydro Product");   
		Hydro2.Add_Myhydro("Myhydro Product");
		Hydro1.search_product("Bundle product"); 
		Hydro1.Addtocart_Bundle("Bundle product");
		Hydro1.search_product("Product");       
		Hydro1.addtocart("Product"); 
		Hydro1.bottles_headerlinks("Bottles & Drinkware");
		Hydro1.Configurable_addtocart_pdp("Product");
		Hydro.minicart_Checkout();
		Hydro.RegaddDeliveryAddress("AccountDetails");
		Hydro.selectshippingaddress("GroundShipping method"); // Need to add 2-day once enabled
		Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
		Hydro.payPal_Payment("PaypalDetails");

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
    Hydro.close_add();
    Hydro.acceptPrivacy();
}
}
