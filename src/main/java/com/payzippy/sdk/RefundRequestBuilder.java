package com.payzippy.sdk;

public interface RefundRequestBuilder
{
	public RefundRequestBuilder setMerchantId(String merchantId);
	public RefundRequestBuilder setMerchantKeyId(String merchantKeyId);
	public RefundRequestBuilder setMerchantTransactionId(String merchantTransactionId);
	public RefundRequestBuilder setPayzippySaleTransactionId(String payzippySaleTransactionId);
	public RefundRequestBuilder setHashMethod(String hashMethod);
	public RefundRequestBuilder setRefundAmount(String refundAmount);
	public RefundRequestBuilder putParams(String key,Object value);
	public RefundRequest build(String secretKey)throws Exception;

}
