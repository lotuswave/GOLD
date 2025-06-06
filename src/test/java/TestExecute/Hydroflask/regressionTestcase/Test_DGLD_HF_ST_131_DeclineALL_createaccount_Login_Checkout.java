package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_131_DeclineALL_createaccount_Login_Checkout {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_DeclineALL_createaccount_Login_Checkout() throws Exception {

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
            if(Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")) {
            	System.out.println("Executed In PRE-PROD");
            	Hydro.updatePaymentAndSubmitOrder("CCMastercard");
    			Hydro.Logout();
    			Hydro.click_singinButton();
    			Hydro.login_Hydroflask("AccountDetails");
            }else {
            	System.out.println("Executed In PROD");
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
		System.setProperty("configFile", "Hydroflask\\config.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.decline_All();

	}

}
