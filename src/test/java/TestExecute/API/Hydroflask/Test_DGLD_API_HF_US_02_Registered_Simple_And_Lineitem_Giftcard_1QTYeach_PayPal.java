package TestExecute.API.Hydroflask;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestExecute.API.Hydroflask.Test_DGLD_API_HF_US_01_01_Guest_1LineItem_QTY2_CC.JsonFormatter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test_DGLD_API_HF_US_02_Registered_Simple_And_Lineitem_Giftcard_1QTYeach_PayPal {
	private String apiKey;
    private String orderNumber;
    public Integer itemId;
    public String sku;
    public Integer QTYOrder;
    public String customerEmail;
    public String increment_id;
    public String deliveryNumber;
    public String MagentoOrder_ID;
    public Integer firstItemId;
    public Integer SecondItemId;
    
    @Test(priority = 1)
    public void generateApiKey() {
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/V1/integration/admin/token";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String requestBody = "{\"username\":\"qa-api\", \"password\":\"R2K261GbPSaI\"}";
        request.body(requestBody);

        Response response = request.post();
        Assert.assertEquals(response.getStatusCode(), 200, "API Key generation failed");

        apiKey = response.getBody().asString().replaceAll("^\"|\"$", "");
        System.out.println("API Key: " + apiKey);
    }

    @Test(priority = 2, dependsOnMethods = "generateApiKey")
    public void getOrderCopy() {
    	MagentoOrder_ID="";
    	RestAssured.baseURI = "https://na-preprod.hele.digital/rest/hydroflask/V1/orders/"+MagentoOrder_ID+"/";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        Response response = request.get();

        Assert.assertEquals(response.getStatusCode(), 200, "Get Order Copy failed");
        String jsonResponse = response.getBody().asString();
        String formattedJson = JsonFormatter.formatJson(jsonResponse);

//      System.out.println("Get Order Copy Response: " + formattedJson);

        orderNumber = response.path("increment_id");
        System.out.println("OderNumber: " + orderNumber);
        customerEmail = response.path("customer_email");
        System.out.println("Customer Email: " + customerEmail);
        List<Map<String, Object>> items = response.jsonPath().getList("items");
        if (items != null && !items.isEmpty()) {
            if (items.size() >= 3) { 
                 firstItemId = (Integer) items.get(0).get("item_id");
                if (firstItemId != null) {
                    System.out.println("First item_id: " + firstItemId);
                } else {
                    System.out.println("First item_id is null.");
                }
                 SecondItemId = (Integer) items.get(2).get("item_id");
                if (SecondItemId != null) {
                    System.out.println("Third item_id: " + SecondItemId);
                } else {
                    System.out.println("Third item_id is null.");
                }
            } else {
                System.out.println("Not enough items to get the first and Second item_id.");
            }
        } else {
            System.out.println("No items found in the response.");
            Assert.fail("No items found in the order copy");
        }
    }
        
    
    @Test(priority = 3, dependsOnMethods = {"generateApiKey", "getOrderCopy"})
    public void shipOrder() {
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/all/V1/order/" + MagentoOrder_ID + "/ship";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);

        String deliveryNumberBase = "02102";
        String deliveryNumberSuffix = generateRandomNumber(4);
        deliveryNumber = deliveryNumberBase + deliveryNumberSuffix;
        String trackingNumberBase = "1025433";
        String trackingNumberSuffix = generateRandomNumber(6);
        String trackingNumber = trackingNumberBase + trackingNumberSuffix;

        String requestBody = "{\n" +
                "    \"notify\": false,\n" + // Corrected: notify should be boolean, not string
                "    \"items\": [\n" +
                "        {\n" +
                "            \"order_item_id\": " + firstItemId + ",\n" +
                "            \"qty\": 1.0\n" +
                "        },\n" + 
                "        {\n" +
                "            \"order_item_id\": " + SecondItemId + ",\n" +
                "            \"qty\": 1.0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"tracks\": [\n" +
                "        {\n" +
                "            \"track_number\": \"" + trackingNumber + "\",\n" +
                "            \"title\": \"FedEx\",\n" +
                "            \"carrier_code\": \"fedex\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"arguments\": {\n" +
                "        \"extension_attributes\": {\n" +
                "            \"delivery_number\": \"" + deliveryNumber + "\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        request.body(requestBody);

        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "Ship order failed");
        System.out.println("Ship Order Response: " + response.getBody().asString());
        System.out.println("Request Body: " + requestBody);
    }

    @Test(priority = 4, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder"})
    public void invoice() {
        RestAssured.baseURI = "https://na-preprod.hele.digital/rest/hydroflask/V1/order/" + MagentoOrder_ID + "/invoice";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + apiKey);
        String requestBody = "{\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"order_item_id\": " + firstItemId + ",\n" +
                "            \"qty\": 1.0\n" +
                "        },\n" + 
                "        {\n" + 
                "            \"order_item_id\": " + SecondItemId + ",\n" +
                "            \"qty\": 1.0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"notify\": false,\n" +
                "    \"appendComment\": false,\n" +
                "    \"capture\": true,\n" +
                "    \"arguments\": {\n" +
                "        \"extension_attributes\": {\n" +
                "            \"delivery_number\": \"" + deliveryNumber + "\",\n" +
                "            \"oracle_customer_number\": \"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        request.body(requestBody);

        Response response = request.post();

        Assert.assertEquals(response.getStatusCode(), 200, "Invoice creation failed");
        System.out.println("Invoice Response: " + response.getBody().asString());
        System.out.println("Request Body: " + requestBody);
    }
	
	
	///***Create RMA***///
    	@Test(priority = 5, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder","invoice"})
 public void createRma() throws InterruptedException {
		Thread.sleep(30000);
	System.out.println(apiKey);
     RestAssured.baseURI = "https://na-preprod.hele.digital/rest/all/V1/returns/create-rma";

     RequestSpecification request = RestAssured.given();
     request.header("Content-Type", "application/json"); // Or "text/plain" as in Postman
     request.header("Authorization", "Bearer " + apiKey);// Use the provided token
     
     String Increment_Base = "15735ea9";
     String incrementSuffix = generateRandomNumber(5); // Generate 6 random digits
      increment_id = Increment_Base + incrementSuffix;
     
     String requestBody = "{\n" +
    	        "    \"increment_id\": \""+increment_id+"\",\n" +
    	        "    \"order_increment_id\": \""+orderNumber+"\",\n" +
    	        "    \"external_rma_id\": \""+increment_id+"\",\n" +
    	        "    \"customer_custom_email\": \"" + customerEmail + "\",\n" + // Use customerEmail variable
    	        "    \"items\": [\n" +
    	        "        {\n" +
    	        "            \"order_item_id\": "+firstItemId+",\n" +
    	        "            \"quantity_to_return\": 1,\n" +
    	        "            \"entered_custom_attributes\": [\n" +
    	        "                {\n" +
    	        "                    \"attribute_code\": \"reason\",\n" +
    	        "                    \"value\": \"2\"\n" +
    	        "                },\n" +
    	        "                {\n" +
    	        "                    \"attribute_code\": \"compensationMethod\",\n" +
    	        "                    \"value\": \"return-for-refund\"\n" +
    	        "                }\n" +
    	        "            ],\n" +
    	        "            \"selected_custom_attributes\": []\n" +
    	        "        }\n" +
    	        "    ]\n" +
    	        "}";
    

     request.body(requestBody);
     Response response = request.post();
     String jsonResponse = response.getBody().asString();

     JSONObject jsonObject = new JSONObject(jsonResponse);
//System.out.println(jsonObject);
     Assert.assertEquals(response.getStatusCode(), 200, "Create RMA failed");

     // Validations
     Assert.assertEquals(jsonObject.getString("customer_custom_email"), customerEmail, "Customer email mismatch");
     Assert.assertEquals(jsonObject.getString("increment_id"), increment_id, "Increment ID mismatch");
     Assert.assertEquals(jsonObject.getString("order_increment_id"), orderNumber, "Order increment ID mismatch");
     Assert.assertEquals(jsonObject.getString("status"), "authorized", "Status mismatch");
//     Assert.assertEquals(jsonObject.getJSONArray("items").getJSONObject(0).getInt("order_item_id"), itemId, "Order item ID mismatch");

     System.out.println("Create RMA Response: " + jsonObject.toString(4));
 }
     

	//***Update RMA***///
//	@Test(priority = 6, dependsOnMethods = "generateApiKey")
// public void updateRma() {
//     RestAssured.baseURI = "https://na-preprod.hele.digital/rest/all/V1/returns/update-rma/15110ea75021";
//
//     RequestSpecification request = RestAssured.given();
//     request.header("Content-Type", "application/json"); // Or "text/plain" as in Postman
//     request.header("Authorization", "Bearer" + apiKey); // Use the provided token
//
//     String requestBody = "{\r\n" + 
//             "}";
//
//     request.body(requestBody);
//
//     Response response = request.put();
//
//     Assert.assertEquals(response.getStatusCode(), 200, "Update RMA failed");
//     System.out.println("Update RMA Response: " + response.getBody().asString());
// }
	
	
	///****Post Credit Memo****///
	@Test(priority = 6, dependsOnMethods = {"generateApiKey", "getOrderCopy", "shipOrder","invoice","createRma"})
 public void postCreditMemo() {
     RestAssured.baseURI = "https://na-preprod.hele.digital/rest/V1/returns/"+increment_id+"/refund";

     RequestSpecification request = RestAssured.given();
     request.header("Content-Type", "application/json"); // Or "text/plain" as in your Postman
     request.header("Authorization", "Bearer " + apiKey);// Use the provided token

     String requestBody = "{\n" +
             "    \"type\": \"approved_return\",\n" +
             "    \"order_item_ids\": [\n" +
             "        {\n" +
             "            \"qty\": \"1.0\",\n" +
             "            \"order_item_id\": \""+firstItemId+"\"\n" +
             "        }\n" +
             "    ],\n" +
             "    \"refund_shipping\": false\n" +
             "}";


     request.body(requestBody);

     Response response = request.post();
     String jsonResponse = response.getBody().asString();

     JSONObject jsonObject = new JSONObject(jsonResponse);

     Assert.assertEquals(response.getStatusCode(), 200, "Post Credit Memo failed");

     // Validate credit_memo_id
     JSONArray successArray = jsonObject.getJSONArray("success");
     if (successArray.length() > 0) {
         JSONObject successObject = successArray.getJSONObject(0);
         if (successObject.has("credit_memo_id")) {
             int creditMemoId = successObject.getInt("credit_memo_id");
             System.out.println("Credit Memo ID: " + creditMemoId);
             // Optionally, you can add further validations on the credit_memo_id
         } else {
             Assert.fail("credit_memo_id is missing in the response");
         }
     } else {
         Assert.fail("success array is empty");
     }

     System.out.println("Post Credit Memo Response: " + jsonObject.toString(4));
 }
     
 	

	
private String generateRandomNumber(int length) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
        sb.append(random.nextInt(10)); // Append a random digit (0-9)
    }
    return sb.toString();
}

public static class JsonFormatter {
    public static String formatJson(String jsonString) {
        try {
            JSONTokener tokener = new JSONTokener(new StringReader(jsonString));
            JSONObject jsonObject = new JSONObject(tokener);
            return jsonObject.toString(4);
        } catch (org.json.JSONException e) {
            return "Error parsing JSON: " + jsonString;
        }
    }
}
	}
