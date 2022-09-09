package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Myorderlisting_025_alternate_path_user_selects_pagination {
	String datafile = "oxo//OXOTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void My_Orders_listing_page() throws Exception {

		try {
	Oxo.click_singinButton();
	Oxo.user_SignIn("AccountDetails");
	Oxo.navigate_To_MyAccount();
	Oxo.My_Order_list();

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
 System.setProperty("configFile", "oxo\\config.properties");
  Login.signIn();

}

}

