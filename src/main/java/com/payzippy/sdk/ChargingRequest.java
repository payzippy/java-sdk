package com.payzippy.sdk;

import java.util.HashMap;
import java.util.Map;

import com.payzippy.sdk.utils.HashUtil;
import com.payzippy.sdk.utils.RequestUtil;
import com.payzippy.sdk.utils.ValidityCheck;

public class ChargingRequest
{
	private final Map<String, Object> requestParams;

	public ChargingRequest(Map<String, Object> requestParams)
	{
		this.requestParams = requestParams;
	}

	public static class Builder implements ChargingRequestBuilder
	{
		private Map<String, Object> requestParams = new HashMap<String, Object>();

		public ChargingRequestBuilder setMerchantId(String merchantId)
		{
			requestParams.put(Constants.MERCHANT_ID, merchantId);
			return this;

		}

		public ChargingRequestBuilder setBuyerEmailId(String buyerEmailId)
		{
			requestParams.put(Constants.BUYER_EMAIL_ADDRESS, buyerEmailId);
			return this;

		}

		public ChargingRequestBuilder setMerchantTransactionId(String merchantTransactionId)
		{
			requestParams.put(Constants.MERCHANT_TRANSACTION_ID, merchantTransactionId);
			return this;

		}

		public ChargingRequestBuilder setPaymentMethod(String paymentMethod)
		{
			requestParams.put(Constants.PAYMENT_METHOD, paymentMethod);
			return this;

		}

		public ChargingRequestBuilder setEmiMonths(String emiMonths)
		{
			requestParams.put(Constants.EMI_MONTHS, emiMonths);
			return this;

		}

		public ChargingRequestBuilder setCurrency(String currency)
		{
			requestParams.put(Constants.CURRENCY, currency);
			return this;

		}

		public ChargingRequestBuilder setMerchantKeyId(String merchantKeyId)
		{
			requestParams.put(Constants.MERCHANT_KEY_ID, merchantKeyId);
			return this;

		}

		public ChargingRequestBuilder setBankName(String bankName)
		{
			requestParams.put(Constants.BANK_NAME, bankName);
			return this;

		}

		public ChargingRequestBuilder putParams(String key, Object value)
		{
			requestParams.put(key, value);
			return this;
		}

		public ChargingRequestBuilder setUiMode(String uiMode)
		{
			requestParams.put(Constants.UI_MODE, uiMode);
			return this;

		}

		public ChargingRequestBuilder setTransactionAmount(String transactionAmount)
		{
			requestParams.put(Constants.TRANSACTION_AMOUNT, transactionAmount);
			return this;

		}

		public ChargingRequestBuilder setHashMethod(String hashMethod)
		{
			requestParams.put(Constants.HASH_METHOD, hashMethod.toUpperCase());
			return this;

		}

		public ChargingRequestBuilder setTransactionType(String transactionType)
		{
			requestParams.put(Constants.TRANSACTION_TYPE, transactionType);
			return this;

		}

		public ChargingRequest build(String secretKey) throws Exception
		{
			// TODO: validity check
			ValidityCheck.validateChargeParams(this.requestParams);

			if (!requestParams.containsKey(Constants.HASH))
			{
				requestParams.put(Constants.HASH, HashUtil.generateHash(requestParams, secretKey, this.requestParams
				        .get(Constants.HASH_METHOD).toString()));
			}
			return new ChargingRequest(this.requestParams);
		}

	}

	public static ChargingRequestBuilder getBuilder()
	{
		return new Builder();
	}

	public Map<String, Object> getRequestParams()
	{
		return requestParams;
	}

	public String getMerchantId()
	{
		return requestParams.get(Constants.MERCHANT_ID).toString();
	}

	public String getBuyerEmailId()
	{
		return requestParams.get(Constants.BUYER_EMAIL_ADDRESS).toString();
	}

	public String getMerchantTransactionId()
	{
		return requestParams.get(Constants.MERCHANT_TRANSACTION_ID).toString();
	}

	public String getTransactionType()
	{
		return requestParams.get(Constants.TRANSACTION_TYPE).toString();
	}

	// Transaction Amount in paise

	public String getTransactionAmount()
	{
		return requestParams.get(Constants.TRANSACTION_AMOUNT).toString();
	}

	public String getPaymentMethod()
	{
		return requestParams.get(Constants.PAYMENT_METHOD).toString();
	}

	public String getBankName()
	{
		return requestParams.get(Constants.BANK_NAME).toString();
	}

	public String getEmiMonths()
	{
		return requestParams.get(Constants.EMI_MONTHS).toString();
	}

	public String getCurrency()
	{
		return requestParams.get(Constants.CURRENCY).toString();
	}

	public String getUiMode()
	{
		return requestParams.get(Constants.UI_MODE).toString();
	}

	public String getHashMethod()
	{
		return requestParams.get(Constants.HASH_METHOD).toString();
	}

	public String getHash()
	{
		return requestParams.get(Constants.HASH).toString();
	}

	public String getMerchantKeyId()
	{
		return requestParams.get(Constants.MERCHANT_KEY_ID).toString();
	}

	public boolean isValid() throws Exception
	{
		return ValidityCheck.validateChargeParams(requestParams);
	}

	public String getUrl(String baseUrl)
	{
		return RequestUtil.getUrl(requestParams, baseUrl);
	}

}
