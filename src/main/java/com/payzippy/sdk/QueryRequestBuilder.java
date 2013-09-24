package com.payzippy.sdk;

public interface QueryRequestBuilder
{
	public QueryRequestBuilder setMerchantId(String merchantId);
	public QueryRequestBuilder setMerchantKeyId(String merchantKeyId);
	public QueryRequestBuilder setMerchantTransactionId(String merchantTransactionId);
	public QueryRequestBuilder setPayzippyTransactionId(String payzippySaleTransactionId);
	public QueryRequestBuilder setHashMethod(String hashMethod);
	public QueryRequestBuilder putParams(String key,Object value);
	public QueryRequest build(String secretKey)throws Exception;
}
