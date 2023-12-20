package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_ST_011_GuestUser_Visa_TAX_2_Items_standard_Shipping {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_GuestUser_Visa_TAX_2_Items_standard_Shipping() throws Exception {
//       for(int i=0;i<3;i++)
//       {
		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			Thread.sleep(5000);
			//String Website=Oxo.URL();
			String Description ="GuestUser_Visa_TAX_2_Items_standard_Shipping";
			Oxo.verifingHomePage();
			Oxo.search_E2E_product("SKU-11315700");
			Oxo.Addtocart("SKU-11315700");
			Oxo.search_E2E_product("SKU-63135700");
			Oxo.Addtocart("SKU-63135700");
			Oxo.minicart_Checkout();
			String Products_details=Oxo.shipping_order_details();
			HashMap<String,String> Shipping=Oxo.Shipingdetails("AccountDetails");
			Oxo.select_Shipping_Method("GroundShipping method");
			Oxo.clickSubmitbutton_Shippingpage();
			HashMap<String,String> data=Oxo.OrderSummaryValidation();
			HashMap<String,String> Payment=Oxo.creditCard_payment("CCVisacard");
			String OrderIdNumber= Oxo.Verify_order_page();
			System.out.println(OrderIdNumber);
			Oxo.Admin("Login Details");
			Oxo.click_Sales();
			HashMap<String,String> Orderstatus1 = Oxo.Admin_Order_Details(OrderIdNumber);
			Oxo.writeOrderNumber(OrderIdNumber, Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"),Products_details,Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("AdminOrdertax"),Orderstatus1.get("AdminOrdertotal"));

			
			
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
//	}
	
	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();
		  Oxo.acceptPrivacy();

	}

}
