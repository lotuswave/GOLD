package TestExecute.OXO.OXO_O2C_E2E;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.GoldOxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_E2E_006_Ecom_RegisterUser_Expresspaypal_1_Item_Standard_Shipping {

	String datafile = "OXO//GoldOxoTestData.xlsx";	
	GoldOxoHelper Oxo=new GoldOxoHelper(datafile,"E2E");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void RegisterUser_Expresspaypal_1_Item_Standard_Shipping() throws Exception {

		try {
			Oxo.prepareOrdersData("OXO_E2E_orderDetails.xlsx");
			Thread.sleep(5000);
			//String Website=Oxo.URL();
			String Description ="RegisterUser_Expresspaypal_1_Item_Standard_Shipping";
			Oxo.verifingHomePage();
			Oxo.click_singinButton();
			Oxo.Usersignin("AccountDetails");
			Oxo.search_E2E_product("SKU-1130780");
			Oxo.Addtocart("SKU-1130780");
			Oxo.minicart_Checkout();
			String Products_details=Oxo.shipping_order_details();
			HashMap<String,String> Payment= Oxo.ExpressPaypal("PaypalDetails");
			Oxo.Paypal_Address();
			HashMap<String,String> Shipping=Oxo.ExpressShipingdetails("AccountDetails");
			Oxo.express_paypal_shipping("PaypalDetails");
			HashMap<String,String> data=Oxo.OrderSummaryValidation();
			Oxo.ExpressOrder();
			String OrderIdNumber= Oxo.Verify_order_page();
			System.out.println(OrderIdNumber);
			Oxo.writeOrderNumber(OrderIdNumber, Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"),Products_details);

			
			
			

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
