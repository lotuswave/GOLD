package TestExecute.Osprey_EMEA.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_PRODHYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSEU_Guest_user_Checkout_Visa_card_Validate_Minicart {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	
	OspreyEMEA_PRODHYVA Osprey_ReEu = new OspreyEMEA_PRODHYVA(datafile,"Minicart");
	OspreyEMEA_PRODHYVA Osprey_ReEu1 = new OspreyEMEA_PRODHYVA(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guest_user_Checkout_Visa_card_Validate_Minicart () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();                          
	        	//Osprey_ReEu.Bagpacks_headerlinks("Backpacks & Bags");
				Osprey_ReEu.search_product("Simple product");
	        	Osprey_ReEu.simple_addtocart("Simple product");
	        	Osprey_ReEu.search_product("Product");      
	        	Osprey_ReEu.addtocart("Product");                                        
	        	Osprey_ReEu.clickontheproduct_and_image("Product");
	        	Osprey_ReEu.minicart_freeshipping();
	        	Osprey_ReEu.minicart_delete("Product");
	        	Osprey_ReEu.minicart_product_close();
	        	Osprey_ReEu.minicart_validation("Product Qunatity");      
	        	Osprey_ReEu1.minicart_Checkout();
	        	Osprey_ReEu1.addDeliveryAddress_Guestuser("Account");
	        	Osprey_ReEu1.selectshippingmethod("GroundShipping method");                 
	        	Osprey_ReEu1.updatePaymentAndSubmitOrder("CCVisacard");
	        
		     
		        
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
