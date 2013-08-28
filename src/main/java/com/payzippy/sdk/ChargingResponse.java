package com.payzippy.sdk;

import java.util.Map;

import com.payzippy.sdk.utils.HashUtil;

public class ChargingResponse
{
	private final Map<String, Object> responseParams;

	public ChargingResponse(Map<String, Object> responseParams)
	{
		this.responseParams = responseParams;
	}

	public Map<String, Object> getResponseParams()
	{
		return responseParams;
	}
	
	public boolean isValidResponse(String secretKey)
	{
		return responseParams.get(Constants.HASH).equals(
		        HashUtil.generateHash(responseParams, secretKey, responseParams.get(Constants.HASH_METHOD).toString()));
	}

	public boolean isFraud()
	{
		return "Accept".equalsIgnoreCase(String.valueOf(responseParams.get(Constants.FRAUD_ACTION)));
	}

	public boolean isSuccess()
	{
		return "SUCCESS".equalsIgnoreCase(String.valueOf(responseParams.get(Constants.TRANSACTION_STATUS)));
	}

	public String getPayzippyTransactionId()
	{
		return String.valueOf(responseParams.get(Constants.PAYZIPPY_TRANSACTION_ID));
	}

	public String getMerchantTransactionId()
	{
		return String.valueOf(responseParams.get(Constants.MERCHANT_TRANSACTION_ID));
	}

	public String getTransactionStatus()
	{
		return String.valueOf(responseParams.get(Constants.TRANSACTION_STATUS));
		
	}

	public String getTransactionResponseMessage()
	{
		return String.valueOf(responseParams.get(Constants.TRANSACTION_RESPONSE_MESSAGE));
	}
	public String getTransactionResponseCode(){
		return String.valueOf(responseParams.get(Constants.TRANSACTION_RESPONSE_CODE));
	}

	public String getFraudAction()
	{
		return String.valueOf(responseParams.get(Constants.FRAUD_ACTION));
	}

	public String getFraudDetails()
	{
		return String.valueOf(responseParams.get(Constants.FRAUD_DETAILS));
	}
	public Integer getTransactionAmount(){
		return Integer.parseInt(String.valueOf(responseParams.get(Constants.TRANSACTION_AMOUNT)));
	}
	public String getPaymentMethod(){
		return String.valueOf(responseParams.get(Constants.PAYMENT_METHOD));
	}
	public String getBankName(){
		return String.valueOf(responseParams.get(Constants.BANK_NAME));
	}
	public Integer getEmiMonths(){
		return Integer.parseInt(String.valueOf(responseParams.get(Constants.EMI_MONTHS)));
	}
	public String getTransactionCurrency(){
		return String.valueOf(responseParams.get(Constants.TRANSACTION_CURRENCY));
	}
	public String getTransactionTime(){
		return String.valueOf(responseParams.get(Constants.TRANSACTION_TIME));
	}
	public String getUdf1(){
		return String.valueOf(responseParams.get(Constants.UDF1));
	}
	public String getUdf2(){
		return String.valueOf(responseParams.get(Constants.UDF2));
	}
	public String getUdf3(){
		return String.valueOf(responseParams.get(Constants.UDF3));
	}
	public String getUdf4(){
		return String.valueOf(responseParams.get(Constants.UDF4));
	}
	public String getUdf5(){
		return String.valueOf(responseParams.get(Constants.UDF5));
	}
	public String getHashMethod(){
		return String.valueOf(responseParams.get(Constants.HASH_METHOD));
	}
	public String getHash(){
		return String.valueOf(responseParams.get(Constants.HASH));
	}
	public String getMerchnatId(){
		return String.valueOf(responseParams.get(Constants.MERCHANT_ID));
	}
	public String getMerchnatKeyId(){
		return String.valueOf(responseParams.get(Constants.MERCHANT_KEY_ID));
	}
	public Boolean IsInternational(){
		return String.valueOf(responseParams.get(Constants.IS_INTERNATIONAL)).equals("true");
	}
	public String getVersion(){
		return String.valueOf(responseParams.get(Constants.VERSION));
	}
	
}	
