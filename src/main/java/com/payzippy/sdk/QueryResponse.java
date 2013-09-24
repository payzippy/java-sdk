package com.payzippy.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.payzippy.sdk.utils.HashUtil;

public class QueryResponse
{
	private final Map<String, Object> responseParams;

	public class TransactionResponse
	{
		private final Map<String, Object> dataParams;

		public TransactionResponse(Map<String, Object> params)
		{
			this.dataParams = params;
		}

		public Map<String, Object> getTransactionResponseParams()
		{
			return dataParams;
		}

		public String getTransactionStatus()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_STATUS));
		}

		public String getTransactionResponseCode()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_RESPONSE_CODE));
		}

		public String getFraudAction()
		{
			return String.valueOf(dataParams.get(Constants.FRAUD_ACTION));
		}

		public String getFraudDetails()
		{
			return String.valueOf(dataParams.get(Constants.FRAUD_DETAILS));
		}

		public Integer getTransactionAmount()
		{
			return Integer.parseInt(String.valueOf(dataParams.get(Constants.TRANSACTION_AMOUNT)));
		}

		public String getMerchantTransactionId()
		{
			return String.valueOf(dataParams.get(Constants.MERCHANT_TRANSACTION_ID));
		}

		public String getPayzippyTransactionId()
		{
			return String.valueOf(dataParams.get(Constants.PAYZIPPY_TRANSACTION_ID));
		}

		public String getBankArn()
		{
			return String.valueOf(dataParams.get(Constants.BANK_ARN));
		}

		public String getPaymentMethod()
		{
			return String.valueOf(dataParams.get(Constants.PAYMENT_METHOD));
		}

		public String getTransactionTime()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_TIME));
		}

		public String getTransactionCurrency()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_CURRENCY));
		}

		public String getTransactionType()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_TYPE));
		}

		public Integer getEmiMonths()
		{
			return Integer.parseInt(String.valueOf(dataParams.get(Constants.EMI_MONTHS)));
		}

		public String getTransactionResponseMessage()
		{
			return String.valueOf(dataParams.get(Constants.TRANSACTION_RESPONSE_MESSAGE));

		}

	}

	private List<TransactionResponse> transactionResponse = new ArrayList();

	public QueryResponse(Map<String, Object> responseParams)
	{
		this.responseParams = responseParams;
		List<Map<String, Object>> dataParams = (ArrayList<Map<String, Object>>) responseParams.get(Constants.DATA);
		for (Map<String, Object> dataParam : dataParams)
		{
			transactionResponse.add(new TransactionResponse(dataParam));
		}
	}

	public QueryResponse(String jsonString) throws Exception
	{
		this.responseParams = (new ObjectMapper()).readValue(jsonString, Map.class);
		List<Map<String, Object>> dataParams = (ArrayList<Map<String, Object>>) responseParams.get(Constants.DATA);
		for (Map<String, Object> dataParam : dataParams)
		{
			transactionResponse.add(new TransactionResponse(dataParam));
		}
	}

	public List<TransactionResponse> getTransactionResponse()
	{
		return this.transactionResponse;
	}

	public Map<String, Object> getResponseParams()
	{
		return responseParams;
	}

	public Map<String, Object> generateHashParams(Map<String, Object> params)
	{

		Map<String, Object> hashParams = new HashMap<String, Object>();
		for (String key : params.keySet())
		{
			if (key.equals("data"))
			{
				ArrayList<Map<String, Object>> dataParams = (ArrayList<Map<String, Object>>) params.get(key);
				if (dataParams.size() > 0)
				{
					for (Integer i = 0; i < dataParams.size(); i++)
					{
						for (String dKey : dataParams.get(i).keySet())
						{
							hashParams.put("data[" + i.toString() + "]." + dKey,
							        String.valueOf(dataParams.get(i).get(dKey)));
						}
					}
				}
				else
				{
					hashParams.put("data", "");
				}

			}
			else
			{
				hashParams.put(key, String.valueOf(params.get(key)));
			}
		}
		return hashParams;
	}

	public int getStatusCode()
	{
		return Integer.parseInt(String.valueOf(responseParams.get(Constants.STATUS_CODE)));
	}

	public String getStatusMessage()
	{
		return String.valueOf(responseParams.get(Constants.STATUS_MESSAGE));
	}

	public boolean isValidResponse(String secretKey)
	{
		if (getStatusCode() == 200)
		{
			return responseParams.get(Constants.HASH).equals(
			        HashUtil.generateHash(generateHashParams(responseParams), secretKey,
			                responseParams.get(Constants.HASH_METHOD).toString()));
		}
		else
		{
			return false;
		}
	}

	public String getHashMethod()
	{
		return String.valueOf(responseParams.get(Constants.HASH_METHOD));
	}

	public String getHash()
	{
		return String.valueOf(responseParams.get(Constants.HASH));
	}

	public String getMerchnatId()
	{
		return String.valueOf(responseParams.get(Constants.MERCHANT_ID));
	}

	public String getMerchnatKeyId()
	{
		return String.valueOf(responseParams.get(Constants.MERCHANT_KEY_ID));
	}

	public String getErrorCode()
	{
		return String.valueOf(responseParams.get(Constants.ERROR_CODE));
	}

	public String getErrorMessage()
	{
		return String.valueOf(responseParams.get(Constants.ERROR_MESSAGE));
	}

}
