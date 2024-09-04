package TestComponent.Hydroflask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;


public class GoldHydroHelper_E2E {
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHydroHelper_E2E(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}
	public void acceptPrivacy()throws Exception {
		Thread.sleep(2000);
		Common.clickElement("id", "truste-consent-button");
		Thread.sleep(4000);
		int n= Common.findElements("xpath","//button[@class='needsclick klaviyo-close-form go2855587515 kl-private-reset-css-Xuajs1']").size();
		if(n>0) {Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@class='needsclick klaviyo-close-form go2855587515 kl-private-reset-css-Xuajs1']");
		}else {
			Thread.sleep(2000);
		}
		Thread.sleep(4000);
		int n1= Common.findElements("xpath","//button[@aria-label='Close dialog']").size();
		if(n1>0) {Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@aria-label='Close dialog']");
		}else {
			Thread.sleep(2000);
		}
	}

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("Home Page")
							|| Common.getPageTitle().contains("Hydro Flask"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}

	}
	public void prepareOrdersData(String fileName) {
		// TODO Auto-generated method stub
		try{


			File file=new File(System.getProperty("user.dir")+"/src/test/resources/TestData/Hydroflask/"+ fileName);
			XSSFWorkbook workbook;
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			if(!(file.exists()))
			{
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("OrderDetails");
			CellStyle cs = workbook.createCellStyle();
			CellStyle ps = workbook.createCellStyle();
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			ps.setFillForegroundColor(IndexedColors.RED.getIndex());
			Font f = workbook.createFont();
			f.setBold(true);
			cs.setFont(f);
			cs.setAlignment(HorizontalAlignment.RIGHT);
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Orders Details");
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("S.No");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("Website");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			
			cell.setCellStyle(cs);
			cell.setCellValue("Test scenario Description");
			cell = row.createCell(3);
			
			cell.setCellStyle(cs);
			cell.setCellValue("SKU");
			cell = row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Order Number");
			cell = row.createCell(5);
			
			cell.setCellStyle(cs);
			cell.setCellValue("Order Status Magento");
           cell = row.createCell(6);
		
			
			rowcount=2;
			}

			else
			{
			workbook = new XSSFWorkbook(new FileInputStream(file));
			sheet=workbook.getSheet("OrderDetails");
			rowcount=sheet.getLastRowNum()+1;
			}
	    	FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();



			} catch (Exception e) {
			e.printStackTrace();
			}
	}
	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void login_Hydroflask(String dataSet) {

		try {
			   Sync.waitPageLoad();
			    Thread.sleep(4000);
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Thread.sleep(4000);
			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		//String product = "25 oz Wine Bottle";
		System.out.println(product);
		try
		{
        Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
     	String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
     	Thread.sleep(4000);
     	Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
     	"User should able to click on the search button", "Search expands to the full page",
     	"Sucessfully search bar should be expand"); 
     	WebElement serachbar=Common.findElement("xpath", "//input[@id='autocomplete-0-input']");
        serachbar.sendKeys(product);
        Common.actionsKeyPress(Keys.ENTER);
    	Sync.waitPageLoad();
    	Thread.sleep(4000);
	Thread.sleep(8000);
             }  
		 catch (Exception | Error e) {
			e.printStackTrace();
		
			Assert.fail();
		}

	}
	public void addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			selectColor(Dataset);
			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}
	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");

			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}

	public String website() throws Exception {
		// TODO Auto-generated method stub
		String Website="";
		 Website=Common.getPageTitle().replace("| Vacuum Insulated Stainless Steel Water Bottles ", "").trim();
	     System.out.println(Website);
		return Website;

		}
	public void minicart_Checkout() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
				click_minicart();
				Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
				String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
				System.out.println(minicart);
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath", "//button[@title='Checkout']");
				Common.clickElement("xpath", "//button[@title='Checkout']");
				Sync.waitPageLoad();
				Thread.sleep(7000);
				Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
				String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
				System.out.println(checkout);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog(
						"validating the navigation to the shipping page when we click on the checkout ",
						"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
						Common.getscreenShot("Failed to navigate to the shipping page"));

				Assert.fail();
			}

		}
	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			if(openminicart.contains("active")) {
				Thread.sleep(3000);
			} else {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			
			
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();

		}
	}
	public void selectColor(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String color1 = data.get(Dataset).get("Color");
		
		 Sync.waitElementPresent(30, "xpath", "//div[@class='m-swatch-group__container']//div");

	        // Find all color options
	        List<WebElement> colorOptions = Common.findElements("xpath", "//div[@class='m-swatch-group__container']//div");

	        // Iterate through color options to find and click the matching color
	        for (WebElement option : colorOptions) {
	            String colorLabel = option.getAttribute("data-option-label");
	            if (colorLabel.contains(color1)) {
	                Thread.sleep(2000); // Optional: Adjust or remove based on specific needs
	                option.click();
	                return; // Exit after selecting the color
	            }
	       
		}
}
	public void applayLoyaltyCoupon(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		//String color1 = data.get(Dataset).get("Color");
		String  loyalityPoints="";
		 Sync.waitElementPresent(30, "xpath", "//div[@class='yotpo-point-balance-text']");
          int loyalitySize=Common.findElements("xpath", "//div[@class='yotpo-point-balance-text']").size();
          
          Common.clickElement("xpath", "//input[@aria-controls='vs1__listbox']");
          Thread.sleep(2000);
          Common.clickElement("xpath", "//input[@aria-controls='vs1__listbox']");
		
}
	public HashMap<String, String> shipingAddresDetails(String dataSet) throws Exception{
		
		HashMap<String, String> Shippingaddress = new HashMap<String, String>();
		try{
			Thread.sleep(5000);
			
			int a=Common.findElements("xpath", "//span[contains(text(),'New Address')]").size();

			if(a>0)
			{
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			}
		else{
			
			}
			Thread.sleep(5000);
			int b=Common.findElements("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']").size();

			if(b>0)
			{
				Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
			}
		else{
			
			}
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		Sync.waitElementPresent("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
		
		Thread.sleep(3000);
		Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
		Thread.sleep(5000);
		String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
		String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText(); Shippingaddress.put("ShippingState", Shippingstate); Sync.waitElementPresent("xpath", "//input[@name='city']");
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
		Thread.sleep(2000);
		//Common.textBoxInputClear("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(5000);
		String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
		System.out.println("*****" + ShippingZip + "*******");
		Shippingaddress.put("ShippingZip", ShippingZip);
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//input[@name='telephone']");
		Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
		Thread.sleep(3000);
		int c=Common.findElements("xpath", "//button[@class='a-btn a-btn--primary action primary action-save-address']").size();

		if(c>0)
		{
			Common.clickElement("xpath", "//button[@class='a-btn a-btn--primary action primary action-save-address']");}
		else{

		}

		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ENTER);
		}
		catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		}
		System.out.println(Shippingaddress);
		return Shippingaddress;
		}
	
	public void selectshippingaddress(String Dataset) {
		String method = data.get(Dataset).get("methods");

		try {
			Thread.sleep(4000);
			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method + "')]");
				Common.clickElement("xpath", "//td[contains(text(),'" + method + "')]");
			} else {

				Assert.fail();

			}
		} catch (Exception | Error e) {
			e.printStackTrace();

			Assert.fail();
		}
	}
	
	public void clickSubmitbutton_Shippingpage() {
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
			Assert.fail();
		}
	}
	public String creditCardPayment(String dataSet) throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);

			Common.clickElement("xpath", "//span[text()='Place Order']");
			Common.refreshpage();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				//Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']//p//span").size() > 0) {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//strong");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[@class='checkout-success']//span").size() > 0) {
					Common.getText("xpath", "//div[@class='checkout-success']//span");
					System.out.println(order);

				}
			
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}

		}
		return order;
	}
	public String addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number = "";
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {

				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
             	   Thread.sleep(10000);
             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
           	   {
           		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
           		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
           		   Thread.sleep(5000);
           		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
               	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
           		   
           	   }
           	   else if(Common.getCurrentURL().contains("/success/"))
           	   {
           	    String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
           	    System.out.println(sucessmessage);
           	   }
           	   else
           	   {
           		   Assert.fail();
           	   }
             	   
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					
					Common.switchToDefault();

				}

			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	             	   Thread.sleep(10000);
	             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
	              	   {
	              		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	              		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	              		   Thread.sleep(5000);
	              		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                  	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                  	   Thread.sleep(8000);
	                  	 String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
		              	    System.out.println(sucessmessage);
	              		   
	              	   }
	              	   else if(Common.getCurrentURL().contains("/success/"))
	              	   {
	              	    String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
	              	    System.out.println(sucessmessage);
	              	   }
	              	   else
	              	   {
	              		   Assert.fail();
	              	   }
	             	   
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					
					Common.switchToDefault();

				}

			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		expectedResult = "credit card fields are filled with the data";

		return Number;
	}
	
	public void Myhydro_Graphic(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize Now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydrographic("Graphic");
			Myhydro_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),' Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
//			Common.refreshpage();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}
	public void Myhydro_bottle(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitPageLoad();
		Thread.sleep(8000);
		try {
			if (Dataset.equals("21 oz")) {
				Thread.sleep(8000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='21 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='21 oz']");
				Thread.sleep(4000);
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				
			} else if (Dataset.equals("40 oz")) {
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='40 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='40 oz']");
				Thread.sleep(4000);
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				
			} else if (Dataset.equals("32 oz")) {
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='32 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='32 oz']");
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}

	public void hydro_bottle_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}

	public void hydro_cap_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Cap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			
			hydro_select_cap("Wide Mouth Flex Sip Lid");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			

			Assert.fail();
		}

	}

	public void hydro_strap_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Strap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			
			hydro_select_strap("Flex Strap - Long");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			Thread.sleep(3000);
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			

			Assert.fail();
		}

	}

	public void hydro_boot_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String boot = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			

			Assert.fail();
		}

	}

	public void hydro_select_cap(String Cap) {
		// TODO Auto-generated method stub
		try {
			if (Cap.contains("Flex Cap")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Cap + "']")
						.getAttribute("class");
				System.out.println(name);
				
			} else if (Cap.contains("Flex Sip Lid")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Cap + "']")
						.getAttribute("class");
				System.out.println(name);
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			

			Assert.fail();
		}

	}

	public void hydro_select_strap(String Starp) {
		// TODO Auto-generated method stub
		try {
			if (Starp.contains("Flex Cap Strap")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Starp + "']")
						.getAttribute("class");
				System.out.println(name);
				
			} else if (Starp.contains("Long")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Starp + "']")
						.getAttribute("class");
				System.out.println(name);
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			

			Assert.fail();
		}

	}
	public void enraving_Checkout(String Dataset) {
		// TODO Auto-generated method stub
		String Graphic = data.get(Dataset).get("Engraving Graphic");
		String text = data.get(Dataset).get("Engraving");
		System.out.println(text);
		try {
			Thread.sleep(4000);
			click_minicart();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//span[contains(@data-bind,'getEngravingText(item)')]");
			String engraving = Common.findElement("xpath", "//span[contains(@data-bind,'getEngravingText(item)')]")
					.getText();
			System.out.println(engraving);
			System.out.println(text);
			
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			System.out.println(minicart);
			Sync.waitElementPresent(30, "xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Sync.waitPageLoad();
			Thread.sleep(7000);
			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}
	public void Myhydrographic(String Dataset) {
		// TODO Auto-generated method stub
		String graphic = data.get(Dataset).get("Engraving Graphic");
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			
			Sync.waitElementPresent(30, "xpath", "//span[text()='Graphic']");
			Common.clickElement("xpath", "//span[text()='Graphic']");
			int subproductsList = Common.findElements("xpath", "//div[@class='graphic-engraving__wrapper']//button")
					.size();
			for (int i = 0; i < subproductsList; i++) {
				int value = i + 1;
				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				WebElement Graphicnames = Common.findElement("xpath", "//span[@class='graphic-engraving__label']");

				WebElement Graphic = Common.findElement("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				for (int j = 0; j < ListOfSubproducts.size(); j++) {

					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

					if (attributevalue != null) {
					} else {

						if (ListOfSubproducts.get(j).getAttribute("class").contains("graphic-engraving__")
								|| ListOfSubproducts.get(j).getAttribute("class")
										.contains("graphic-engraving__selection__btn active")) {
							Thread.sleep(4000);
							System.out.println(ListOfSubproducts);
							ListOfSubproducts.get(j).click();
							Thread.sleep(4000);

							
						}
					}
				}
			}
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + graphic + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + graphic + "']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}
	public void Myhydro_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Common.findElement("xpath", "//select[@class='quantity-dropdown']");
//			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='quantity-dropdown']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}
	public void Add_Myhydro_Text(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Customize Now']");
			Common.clickElement("xpath", "//span[text()='Customize Now']");
			Thread.sleep(3000);
			Myhydro_bottle("21 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("Black");
			hydro_strap_color("Black");
			hydro_boot_color("Black");
			Myhydro_Engraving("Myhydro Product");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent(20, "xpath", "//button[@class='ATC__btn']");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),' Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}
	public void Myhydro_Engraving(String Dataset) {
		// TODO Auto-generated method stub
		String engravingtext = data.get(Dataset).get("Engraving");
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			
			Sync.waitElementPresent("xpath", "//textarea[@class='text-engraving__input']");
			Common.findElement("xpath", "//textarea[@class='text-engraving__input']").sendKeys(engravingtext);
			String Text = Common.findElement("xpath", "//textarea[contains(@class,'text-engraving__input')]")
					.getAttribute("class");
			System.out.println(Text);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	}
	public void Gift_message(String Dataset) {
		// TODO Auto-generated method stub
		String message=data.get(Dataset).get("message");
		try
		{
			Common.scrollIntoView("xpath", "//span[text()='Add Gift Message']");
			Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
			Thread.sleep(4000);	
			Common.clickElement("xpath", "//span[text()='Add Gift Message']");
			int gift=Common.findElements("xpath", "//button[contains(@class,'action action')]").size();
			System.out.println(gift);
			if(gift>1)
			{
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Delete']");
			Sync.waitPageLoad(40);
			Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
			Common.clickElement("xpath", "//span[text()='Add Gift Message']");
			Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
			Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
			Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
			Common.clickElement("xpath", "//button[@title='Add']");
			Sync.waitPageLoad(40);
			Thread.sleep(2000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
			String Messgae=Common.findElement("xpath", "//div[@class='gift-message-summary']").getText().replace("Message: ", "");
			System.out.println(Messgae);
			
			}
			else
			{
			Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
			Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
			Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
			Common.clickElement("xpath", "//button[@title='Add']");
			Sync.waitPageLoad(40);
			Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
			String Messgae=Common.findElement("xpath", "//div[@class='gift-message-summary']").getText().replace("Message: ", "");
			System.out.println(Messgae);
			
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			
			Assert.fail();
		}
		
	}
	public void minicart_viewcart() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Cart']");
			Common.clickElement("xpath", "//span[text()='View Cart']");
			String viewcart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText();
			Sync.waitPageLoad();
			Thread.sleep(8000);

		} catch (Exception | Error e) {
			e.printStackTrace();

			Assert.fail();

		}

	}
	
	public void Admin(String dataSet) {
		// TODO Auto-generated method stub
		//String pagetitle = data.get(Dataset).get("pageTitle");
		try {
			Sync.waitPageLoad(60);

			Common.openNewTab();
			
			Common.oppenURL("https://na-preprod.hele.digital/heledigitaladmin/admin/dashboard/");

			Sync.waitPageLoad(4000);

			String URL = Common.getPageTitle();
			Common.assertionCheckwithReport(URL.contains("Magento"),
					"Validating the Magento Admin panel",
					"User should able to land on Magento Admin panel", "Sucessfully User lands on the Magento Admin panel",
					"Failed to navigate to the Magento Admin panel");
		}
		catch (Exception | Error e) {
		e.printStackTrace();
		

		Assert.fail();
	}

	        try {
	            if (Common.getCurrentURL().contains("preprod")) {
	                Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
	                Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
	            } else {
	            Sync.waitPageLoad(30);
	            Thread.sleep(3000);
	            Sync.waitElementPresent("name", "loginfmt");
	            Common.textBoxInput("name", "loginfmt","spanem@helenoftroy.com");
	            Common.clickElement("id", "idSIButton9");
	            Sync.waitPageLoad();
	            Thread.sleep(3000);
	            Sync.waitElementPresent(30, "name", "passwd");
	            Common.textBoxInput("name", "passwd","Psv@2024#12");
	            Common.clickElement("id", "idSIButton9");
	            Sync.waitPageLoad();
	            Thread.sleep(4000);
	            Common.clickElement("xpath", "//input[@value='Yes']");
	            Thread.sleep(3000);
	        
	            String URL = Common.getPageTitle();
	            }   
		} 
	catch (Exception | Error e) 
	{
			e.printStackTrace();
			

			Assert.fail();
		}
	
	}
	public void click_Sales() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent("id", "menu-magento-sales-sales");
			Common.clickElement("id", "menu-magento-sales-sales");
			Thread.sleep(2000);
			String Sales = Common.findElement("xpath", "//li[@class='item-sales-order    level-2']").getText();
			System.out.println(Sales);
			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
			// @style='display: none;']");
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}

	
	try {
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//li[@class='item-sales-order    level-2']");
		Common.clickElement("xpath", "//li[@class='item-sales-order    level-2']");
		Sync.waitPageLoad();
		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		
		Assert.fail();
	}
}

	public HashMap<String, String> Admin_Order_Details(String OrderId) {
		HashMap<String, String> Orderstatus1 = new HashMap<String, String>();
		
		//String  OrderIdNumber = "";
		try {
			Common.findElement("xpath", "//input[@aria-label='Search by keyword']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//input[@aria-label='Search by keyword']");
			Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", OrderId);
			Common.actionsKeyPress(Keys.ENTER);
		}
		
		 catch (Exception | Error e) {
				e.printStackTrace();
				
				Assert.fail();
	}
	try {
	Thread.sleep(3000);
	Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");

				Thread.sleep(3000);
				Common.clickElement("xpath", "//td//a[@class='action-menu-item']");
			
			String Orderstatus = Common.findElement("xpath", "//span[@id='order_status']").getText();
			
						System.out.println(Orderstatus);
						Orderstatus1.put("AdminOrderstatus", Orderstatus);
						StringBuilder concatenatedText = new StringBuilder();
						int size = Common.findElements("xpath", "//div[@class='product-sku-block']").size();

						for (int n=1;n<=size;n++) {
	             String sku=Common.findElement("xpath", "(//div[@class='product-sku-block'])["+n+"]").getText().replace("SKU:", "");
	             concatenatedText.append(sku);		  
	           
						}
						String finalsku = concatenatedText.toString();
						  System.out.println(finalsku);
						  Orderstatus1.put("Skus", finalsku);
						    
	}
	
		catch (Exception | Error e) {
			e.printStackTrace();
			
			Assert.fail();
		}
	
		
		return Orderstatus1;

}
	public void writeOrderNumber(String Description,String OrderIdNumber,String Skus, String AdminOrderstatus) throws Exception, IOException
	{
		//String fileOut="";
	try{
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources//TestData/Hydroflask/HYF_E2E_orderDetails.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		sheet = workbook.getSheet("OrderDetails");
		
		if((workbook.getSheet("OrderDetails"))==null)
		{
		sheet = workbook.createSheet("OrderDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders Details");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Order Number");
		rowcount=2;
		
		}
		
		else
		{
		
		sheet=workbook.getSheet("OrderDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		row = sheet.createRow(rowcount);
		cell = row.createCell(0);
		cell.setCellType(CellType.NUMERIC);
		int SNo=rowcount-1;
		cell.setCellValue(SNo);
		cell = row.createCell(1);
		cell.setCellType(CellType.STRING);
		
		cell.setCellValue("Hydroflask");
		
		cell = row.createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Description);
		
		cell = row.createCell(3);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Skus);
		
		cell = row.createCell(4);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(OrderIdNumber);

		cell = row.createCell(5);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(AdminOrderstatus);
		
		System.out.println(OrderIdNumber);
		FileOutputStream fileOut = new FileOutputStream(file);
		
		workbook.write(fileOut);
	
		fileOut.flush();
		fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}