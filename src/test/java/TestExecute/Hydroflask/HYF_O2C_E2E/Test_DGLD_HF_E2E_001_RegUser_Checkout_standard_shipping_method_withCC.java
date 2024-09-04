package TestExecute.Hydroflask.HYF_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHelper_E2E;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_E2E_001_RegUser_Checkout_standard_shipping_method_withCC {
	String datafile = "Hydroflask//GoldHydroTestDataE2E.xlsx";
	 GoldHydroHelper_E2E Hydro = new  GoldHydroHelper_E2E(datafile,"DataSet");


	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Register_user_Checkout_Funtionality_Master_card () throws Exception {

		try {
			Hydro.prepareOrdersData("HYF_E2E_orderDetails.xlsx");
			String Description ="Registered user checkout with Master CC + 4 Items, each qty - 2 + Gift message + Standard shipping method";
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.search_product("Product(T28PS110)"); 
			Hydro.addtocart("Product(T28PS110)");
			Hydro.search_product("Product(HCM001)");
			Hydro.addtocart("Product(HCM001)"); 
			Hydro.search_product("Product(T20CPB001)");  
			Hydro.addtocart("Product(T20CPB001)");  	
			Hydro.search_product("Product(S24SX678)");      
			Hydro.addtocart("Product(S24SX678)");
			Hydro.minicart_viewcart();
			Hydro.Gift_message("GiftMessage");
			Hydro.minicart_Checkout();
			HashMap<String,String> Shipping=Hydro.shipingAddresDetails("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();
            String Ordernumber= Hydro.creditCardPayment("CCMastercard");		
            Hydro.Admin("Login Details");
            Hydro.click_Sales();
            HashMap<String,String> Orderstatus1 = Hydro.Admin_Order_Details(Ordernumber);
            Hydro.writeOrderNumber(Description,Ordernumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"));


		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		//Hydro.close_add();
        Hydro.acceptPrivacy();

	}
}
