package TestExecute.Hydroflask.Archive;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HYF_ST_019_EmployeeCustomer_Checkout_with_2Line_Item_Using_CC_Multiple_GiftCode {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_EmployeeCustomer_Checkout_with_2Line_Item_Using_CC_Multiple_GiftCode () throws Exception {
		
		try {
			Hydro.verifingHomePage();
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("Employee_id");
			Hydro.search_product("Product");      
			Hydro.addtocart("Product"); 
			Hydro.employee_discount();
			Hydro.bottles_headerlinks("Bottles & Drinkware"); 
			Hydro.Configurable_addtocart_pdp("Product");
			Hydro.employee_discount();
			Hydro.minicart_Checkout();
			Hydro.RegaddDeliveryAddress("Employee_id");
            Hydro.selectshippingaddress("GroundShipping method");
            Hydro.Gift_card("Giftcard");
            Hydro.Gift_card("Giftcard1");
            Hydro.updatePaymentAndSubmitOrder("PaymentDetails");

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
