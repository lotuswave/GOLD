package TestExecute.Osprey_US.Osprey_US_O2C_E2E;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.OspreyUSE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_E2E_001_Register_User_Afterpay_2Items_Standard_Shippment {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyUSE2EHelper Osprey_ReEu = new OspreyUSE2EHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Register_User_Afterpay_2Items_Standard_Shippment () throws Exception {

		try {
			 
			 Osprey_ReEu.prepareOrdersData("OspreyUS_E2E_orderDetails.xlsx");
			 String Description ="Register_User_Checkout_Afterpay_2Items_Standard_Shippment";
		     Osprey_ReEu.click_singinButton();
		      Osprey_ReEu.Login_Account("Account");
		        Osprey_ReEu.search_product("Product");
		        Osprey_ReEu.addtocart("Product");
		        Osprey_ReEu.minicart_Checkout();
		        Osprey_ReEu.RegaddDeliveryAddress("Account");
		        Osprey_ReEu.selectshippingmethod("GroundShipping method");
		        Osprey_ReEu.clickSubmitbutton_Shippingpage();
		       String OrderNumber= Osprey_ReEu.After_Pay_payment("Afterpay");
		       Osprey_ReEu.writeOrderNumber(OrderNumber, Description);
	 
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
		System.setProperty("configFile", "Osprey_US\\config.properties");
        Login.signIn();
        

	}
	
	
}
