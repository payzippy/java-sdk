package com.payzippy.sdk;

public interface ChargingRequestBuilder
{
	public ChargingRequestBuilder setMerchantId(String merchantId);
	public ChargingRequestBuilder setBuyerEmailId(String buyerEmailId);
	public ChargingRequestBuilder setMerchantTransactionId(String merchantTransactionId);
	public ChargingRequestBuilder setPaymentMethod(String paymentMethod);
	public ChargingRequestBuilder setMerchantKeyId(String merchantKeyId);
	public ChargingRequestBuilder setCurrency(String currency);
	public ChargingRequestBuilder setTransactionType(String transactionType);
	public ChargingRequestBuilder setUiMode(String uiMode);
	public ChargingRequestBuilder setHashMethod(String hashMethod);
	public ChargingRequestBuilder setTransactionAmount(String transactionAmount);
	public ChargingRequestBuilder setEmiMonths(String transactionType);
	public ChargingRequestBuilder setBankName(String bankName);
	public ChargingRequestBuilder putParams(String key,Object value);
	public ChargingRequest build(String secretKey)throws Exception;
	
	
	

}
