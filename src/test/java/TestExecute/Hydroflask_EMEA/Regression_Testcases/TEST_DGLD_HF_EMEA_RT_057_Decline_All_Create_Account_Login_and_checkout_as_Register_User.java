package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_057_Decline_All_Create_Account_Login_and_checkout_as_Register_User {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Decline_All_Create_Account_Login_and_checkout_as_Register_User() throws Exception {
	
		try {
			Hydro.verifingHomePage();
			Hydro.click_Createaccount();
            Hydro.create_account("New Account Details");
			Hydro.search_product("Product");       
			Hydro.addtocart("Product");                    
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("AccountDetails");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.clickSubmitbutton_Shippingpage();  
            Hydro.updatePaymentAndSubmitOrder("CCMastercard");
            if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage4") ) {
            	System.out.println("Executed In PRE-PROD Environment");
            	String Email=Hydro.EmailID_from_successpage();
    			Hydro.Logout();
    			Hydro.click_singinButton();
    			Hydro.Login_with_Create_Account_Email(Email);
            }else {
            	System.out.println("Executed In PROD Environment");
            }
			
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
		Hydro.decline_All();

	}

}
