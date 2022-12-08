package TestExecute.Hydroflask_Algolia_Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroAlgolia_Helper;
import TestComponent.Hydroflask.GoldHydroLoq_Helper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_06_Algolia_Req006_Search_for_a_product_using_its_singular_pluralform {
	
	

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroAlgolia_Helper Hydro = new GoldHydroAlgolia_Helper(datafile,"Loqata");
   @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
   public void Validation_Algolia_Req_Search_for_a_product_using_differentsynonyms () throws Exception {
       try {
    	   
    	   Hydro.AcceptAll();
           Hydro.verifingHomePage();
           Hydro.Validating_searchs("Cup");
          // Hydro.Hydroflask_logo();
           Hydro.Validating_searchs("Spoons");       	
        
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
   }
	

   
   
   
   
}
