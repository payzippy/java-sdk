java-sdk
========

PayZippy Java SDK for interacting with PayZippy charging, query and refund APIs. <br>
Checkout wiki page for documentation <a> https://github.com/payzippy/java-sdk/wiki/PayZippy-Java-SDK </a>

```
To integrate the SDK.
1. Download the SDK, copy the classes from the folder java-sdk-master/target/classes, make sure the package structure is followed.
2. In the checkout page of your website, use the ChargingRequestBuilder object to set all the parameters of your checkout page and then call build method which return ChargingRequest. Make sure all the mandatory parameters are set in the ChargingRequestBuilder object
3. Then call chargingRequest.getUrl(CHARGING_URL) which return the charging API url with all the parameters by passing the charging API url without the parameters

Sample code snippet to create a charging request object is shown below:

//import the package which contains the payzippy sdk classes
<%@page import="com.payzippy.sdk.*" %>

//set all the parameters in the charging builder object
ChargingRequestBuilder chargingBuilder=ChargingRequest.getBuilder();
chargingBuilder.setBuyerEmailId(request.getParameter("buyer_email_address"));
chargingBuilder.setMerchantTransactionId(request.getParameter("merchant_transaction_id"));
chargingBuilder.setMerchantKeyId(<pass your merchant key id here>);
chargingBuilder.setMerchantId(<pass your merchant id here>);
//similarly set all the mandatory parameters as shown above

//and to set any optional parameter in the charging builder object
chargingBuilder.putParams("buyer_phone_no", <pass the buyer phone number here>);
chargingBuilder.putParams("shipping_address", <pass the buyer shipping address here>);

//call build method which returns ChargingRequest object by passing the secret key
ChargingRequest chargingRequest = chargingBuilder.build(<pass your secret key here>);

//get the url here
String url = chargingRequest.getUrl(<pass the charging API url>);

//if ui_mode is REDIRECT, then
response.sendRedirect(url);

//else if ui_mode is IFRAME
//include the url in the iframe src like the example below
<iframe src="<%=url%>" height="450" width="50%">
</iframe>

For complete documentation, please refer the link https://github.com/payzippy/java-sdk/wiki/PayZippy-Java-SDK.

```
