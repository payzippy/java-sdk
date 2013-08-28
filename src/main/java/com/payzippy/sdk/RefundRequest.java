package com.payzippy.sdk;

import java.util.HashMap;
import java.util.Map;

import com.payzippy.sdk.utils.HashUtil;
import com.payzippy.sdk.utils.RequestUtil;
import com.payzippy.sdk.utils.ValidityCheck;

public class RefundRequest
{
	private Map<String, Object> requestParams = new HashMap<String, Object>();

	public RefundRequest(Map<String, Object> requestParams)
	{
		this.requestParams = requestParams;
	}

	public Map<String, Object> getRequestParams()
	{
		return requestParams;
	}

	private static class Builder implements RefundRequestBuilder
	{
		private Map<String, Object> requestParams = new HashMap<String, Object>();

		public RefundRequestBuilder setMerchantKeyId(String merchantKeyId)
		{
			requestParams.put(Constants.MERCHANT_KEY_ID, merchantKeyId);
			return this;

		}

		public RefundRequestBuilder setHashMethod(String hashMethod)
		{
			requestParams.put(Constants.HASH_METHOD, hashMethod.toUpperCase());
			return this;

		}

		public RefundRequestBuilder setPayzippySaleTransactionId(String payzippySaleTransactionId)
		{
			requestParams.put(Constants.PAYZIPPY_SALE_TRANSACTION_ID, payzippySaleTransactionId);
			return this;

		}

		public RefundRequestBuilder setMerchantId(String merchantId)
		{
			requestParams.put(Constants.MERCHANT_ID, merchantId);
			return this;

		}

		public RefundRequestBuilder setMerchantTransactionId(String merchantTransactionId)
		{
			requestParams.put(Constants.MERCHANT_TRANSACTION_ID, merchantTransactionId);
			return this;

		}

		public RefundRequestBuilder setRefundAmount(String refundAmount)
		{
			requestParams.put(Constants.REFUND_AMOUNT, refundAmount);
			return this;

		}

		public RefundRequestBuilder putParams(String key, Object value)
		{
			requestParams.put(key, value);
			return this;
		}

		public RefundRequest build(String secretKey) throws Exception
		{
			if (!requestParams.containsKey(Constants.HASH))
			{
				requestParams.put(Constants.HASH, HashUtil.generateHash(requestParams, secretKey,
				        this.requestParams.get(Constants.HASH_METHOD).toString()));
			}
			ValidityCheck.validateRefundParams(this.requestParams);
			return new RefundRequest(this.requestParams);

		}

	}

	public static RefundRequestBuilder getBuilder()
	{
		return new Builder();
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

	public String getPayzippySaleTransactionId()
	{
		return requestParams.get(Constants.PAYZIPPY_SALE_TRANSACTION_ID).toString();
	}

	public String getRefundAmount()
	{
		return  requestParams.get(Constants.REFUND_AMOUNT).toString();
	}

	public boolean isValid() throws Exception
	{
		return ValidityCheck.validateRefundParams(requestParams);
	}

	public String getUrl(String baseUrl)
	{
		return RequestUtil.getUrl(requestParams, baseUrl);
	}
	
}
