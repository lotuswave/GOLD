package TestExecute.OXO.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OXO_US_ST_007_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHyvaHelper Oxo=new GoldOxoHyvaHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Add_Gift_Card_Code_in_My_Account_Page_and_Select_the_Gift_Card_Code_at_Payment_Page_and_Place_an_Order() throws Exception {

		try {
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.Add_GiftCode_Myaccount("Giftcard"); 
			Oxo.search_product("Product");
			Oxo.addtocart("Product");
			Oxo.minicart_Checkout();
			Oxo.addDeliveryAddress_registerUser("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.Select_Gift_Code("Giftcard");
			Oxo.clickSubmitbutton_Shippingpage();
			Oxo.giftCardSubmitOrder();
			Oxo.Remove_GiftCode();
			
			
			

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
