
	package TestExecute.OXO.unitTestcase;

	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	import TestComponent.OXO.OxoHelper;
	import TestLib.Common;
	import TestLib.Login;



	public class Test_DGLD_OXO_UT_MyOrderListing_024_NormalPath_UserView_MyOrders_Pagewith_NO_Orders {

	
		String datafile = "OXO//OxoTestData.xlsx";	
		OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void UserView_MyOrders_Pagewith_NO_Orders () throws Exception {
			try {
		       
		// TODO Auto-generated method stub
		Oxo.verifingHomePage();
		Oxo.validateCreateAccountpageNavigation();
		Oxo.validateaccountcreationpassword("AccountDetails");
		Oxo.My_Orders_Page();
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
		
		
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
		
		
		@BeforeTest
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Oxo\\config.properties");
			  Login.signIn();
			 
			  
		  }



	}



