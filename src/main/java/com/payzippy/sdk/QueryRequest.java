package com.payzippy.sdk;

import java.util.HashMap;
import java.util.Map;

import com.payzippy.sdk.utils.HashUtil;
import com.payzippy.sdk.utils.RequestUtil;
import com.payzippy.sdk.utils.ValidityCheck;

public class QueryRequest
{
	private final Map<String, Object> requestParams;

	public QueryRequest(Map<String, Object> requestParams)
	{
		this.requestParams = requestParams;
	}

	private static class Builder implements QueryRequestBuilder
	{
		private Map<String, Object> requestParams = new HashMap<String, Object>();

		public QueryRequestBuilder setMerchantKeyId(String merchantKeyId)
		{
			requestParams.put(Constants.MERCHANT_KEY_ID, merchantKeyId);
			return this;

		}

		public QueryRequestBuilder setMerchantId(String merchantId)
		{
			requestParams.put(Constants.MERCHANT_ID, merchantId);
			return this;

		}

		public QueryRequestBuilder setPayzippyTransactionId(String payzippyTransactionId)
		{
			requestParams.put(Constants.PAYZIPPY_TRANSACTION_ID, payzippyTransactionId);
			return this;

		}

		public QueryRequestBuilder setHashMethod(String hashMethod)
		{
			requestParams.put(Constants.HASH_METHOD, hashMethod.toUpperCase());
			return this;

		}

		public QueryRequestBuilder setMerchantTransactionId(String merchantTransactionId)
		{
			requestParams.put(Constants.MERCHANT_TRANSACTION_ID, merchantTransactionId);
			return this;

		}

		public QueryRequestBuilder putParams(String key, Object value)
		{
			requestParams.put(key, value);
			return this;
		}

		public QueryRequest build(String secretKey) throws Exception
		{
			if (!requestParams.containsKey(Constants.HASH))
			{
				requestParams.put(Constants.HASH, HashUtil.generateHash(requestParams, secretKey,
				        this.requestParams.get(Constants.HASH_METHOD).toString()));
			}
			ValidityCheck.validateQueryParams(this.requestParams);
			return new QueryRequest(this.requestParams);
		}

	}

	public static QueryRequestBuilder getBuilder()
	{
		return new Builder();
	}

	public Map<String, Object> getRequestParams()
	{
		return requestParams;
	}

	public String getMerchantKeyId()
	{
		return requestParams.get(Constants.MERCHANT_KEY_ID).toString();
	}

	public String getMerchantId()
	{
		return requestParams.get(Constants.MERCHANT_ID).toString();
	}

	public String getHashMethod()
	{
		return requestParams.get(Constants.HASH_METHOD).toString();
	}

	public String getHash()
	{
		return requestParams.get(Constants.HASH).toString();
	}

	public String getMerchantTransactionId()
	{
		return requestParams.get(Constants.MERCHANT_TRANSACTION_ID).toString();
	}

	public String getPayzippyTransactionId()
	{
		return requestParams.get(Constants.PAYZIPPY_TRANSACTION_ID).toString();
	}

	public boolean isValid() throws Exception
	{
		return ValidityCheck.validateQueryParams(requestParams);
	}

	public String getUrl(String baseUrl)
	{
		return RequestUtil.getUrl(requestParams, baseUrl);
	}
	
}
