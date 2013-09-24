package com.payzippy.sdk;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.payzippy.sdk.utils.HashUtil;

public class RefundResponse
{
	private final Map<String, Object> responseParams;

	public RefundResponse(Map<String, Object> responseParams)
	{
		this.responseParams = responseParams;
	}

	public RefundResponse(String jsonString) throws Exception
	{
		this.responseParams = (new ObjectMapper()).readValue(jsonString, Map.class);
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
				Map<String, Object> dataParams = (Map<String, Object>) params.get(key);
				if (dataParams.size() > 0)
				{
					for (String dKey : dataParams.keySet())
					{
						hashParams.put("data." + dKey, String.valueOf(dataParams.get(dKey)));
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

	public Map<String, String> getData()
	{
		return (Map<String, String>) responseParams.get(Constants.DATA);
	}

	public String getRefundStatus()
	{
		return String.valueOf(getData().get(Constants.REFUND_STATUS));
	}

	public String getRefundResponseMessasge()
	{
		return String.valueOf(getData().get(Constants.REFUND_RESPONSE_MESSAGE));
	}

	public int getStatusCode()
	{
		return Integer.parseInt(String.valueOf(responseParams.get(Constants.STATUS_CODE)));
	}

	public String getStatusMessage()
	{
		return String.valueOf(responseParams.get(Constants.STATUS_MESSAGE));
	}

	public boolean isSuccess()
	{
		return "SUCCESS"
		        .equals(((Map<String, String>) responseParams.get(Constants.DATA)).get(Constants.REFUND_STATUS));
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

	public String getUdf1()
	{
		return String.valueOf(getData().get(Constants.UDF1));
	}

	public String getUdf2()
	{
		return String.valueOf(getData().get(Constants.UDF2));
	}

	public String getUdf3()
	{
		return String.valueOf(getData().get(Constants.UDF3));
	}

	public String getUdf4()
	{
		return String.valueOf(getData().get(Constants.UDF4));
	}

	public String getUdf5()
	{
		return String.valueOf(getData().get(Constants.UDF5));
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

	public String getMerchantTransactionId()
	{
		return String.valueOf(getData().get(Constants.MERCHANT_TRANSACTION_ID));
	}

	public String getPayzippyTransactionId()
	{
		return String.valueOf(getData().get(Constants.PAYZIPPY_TRANSACTION_ID));
	}

	public Integer getRefundAmount()
	{
		return Integer.parseInt(String.valueOf(getData().get(Constants.REFUND_AMOUNT)));
	}

	public String getRefundResponseCode()
	{
		return String.valueOf(getData().get(Constants.REFUND_RESPONSE_CODE));
	}

	public String getBankArn()
	{
		return String.valueOf(getData().get(Constants.BANK_ARN));
	}

	public String getTransactionTime()
	{
		return String.valueOf(getData().get(Constants.TRANSACTION_TIME));
	}

	public String getTerminalId()
	{
		return String.valueOf(getData().get(Constants.TERMINAL_ID));
	}

	public String getCurrency()
	{
		return String.valueOf(getData().get(Constants.CURRENCY));
	}

}
