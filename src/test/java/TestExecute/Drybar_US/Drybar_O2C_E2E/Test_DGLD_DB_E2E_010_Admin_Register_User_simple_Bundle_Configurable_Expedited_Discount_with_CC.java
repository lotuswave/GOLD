package TestExecute.Drybar_US.Drybar_O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusE2EHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_DB_E2E_010_Admin_Register_User_simple_Bundle_Configurable_Expedited_Discount_with_CC {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusE2EHelper Drybar = new GoldDrybarusE2EHelper(datafile,"Drybar_E2E");;

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_Admin_Register_User_simple_Bundle_Configurable_Expedited_Discount_with_CC () throws Exception {

		try {
		Drybar.prepareOrdersData1("Drybar_Admin_E2E_orderDetails.xlsx");
		String Description="Place order on Admin- Add Simple + Bundle item + Configurable  item + Expedited shipping + Discount + CC";
        String Used_GiftCode = "NULL"; 
        Drybar.Admin_signin("AccountDetails");
        Drybar.Customers();
        Drybar.Allcustomers();
        Drybar.SelectCustomer_Edit("DB_Store");
        Drybar.Click_CreatNewOrders();
        Drybar.Select_store("DB_Store");
        Drybar.Add_product_SKU("DB_Store");
        Drybar.bundle_Item();
        Drybar.Admin_Discount("Discount");
        Drybar.Guestuser_shippingaddress("AccountDetails");
        Drybar.Select_Shipping_method("AccountDetails");
        String OrderNumber= Drybar.Default_Payment_method("CCAmexcard");
        Drybar.click_Sales();
        HashMap<String, String> Orderstatus1= Drybar.order_verfication(OrderNumber);
        Drybar.writeOrderNumber1(Description,OrderNumber,Orderstatus1.get("Skus"),Orderstatus1.get("AdminOrderstatus"),Orderstatus1.get("workato"),Used_GiftCode,Orderstatus1.get("Adminsubtotal"),Orderstatus1.get("Adminshipping"),Orderstatus1.get("Admintax"),Orderstatus1.get("AdminDis"),Orderstatus1.get("Admintotal"),Orderstatus1.get("Email"));
         
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
		System.setProperty("configFile", "Drybar_US\\config.properties");
        Login.signIn();
//        Drybar.close_add();
        

	}

}


