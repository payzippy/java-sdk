package com.payzippy.sdk.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.NumberUtils;

import com.payzippy.sdk.Constants;

public class ValidityCheck
{

	public static boolean isNullOrEmpty(Object o)
	{
		return (o == null)
		        || (o.getClass().toString().equalsIgnoreCase(String.class.toString()) && ((String) o)
		                .equalsIgnoreCase(""));
	}

	public static boolean validateRefundParams(Map<String, Object> params) throws Exception
	{
		// Mandatory Parameters

		// Validating merchant id
		if (isNullOrEmpty(params.get(Constants.MERCHANT_ID))
		        || params.get(Constants.MERCHANT_ID).toString().length() > Constants.MERCHANT_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT_ID");
		}
		// Validating merchant key id
		else if (isNullOrEmpty(params.get(Constants.MERCHANT_KEY_ID))
		        || params.get(Constants.MERCHANT_KEY_ID).toString().length() > Constants.MERCHANT_KEY_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT KEY ID");
		}
		// Validating merchant transaction id
		else if (!params.containsKey(Constants.PAYZIPPY_SALE_TRANSACTION_ID)
		        && (isNullOrEmpty(params.get(Constants.MERCHANT_TRANSACTION_ID)) || params
		                .get(Constants.MERCHANT_TRANSACTION_ID).toString().length() > Constants.PAYZIPPY_SALE_TRANSACTION_ID_MAXLEN))
		{
			throw new IllegalArgumentException("INVALID MERCHANT TRANSACTION ID");
		}
		// Validating payzippy_sale_transaction_id
		else if (!params.containsKey(Constants.MERCHANT_TRANSACTION_ID)
		        && (isNullOrEmpty(params.get(Constants.PAYZIPPY_SALE_TRANSACTION_ID)) || params
		                .get(Constants.PAYZIPPY_SALE_TRANSACTION_ID).toString().length() > Constants.MERCHANT_TRANSACTION_ID_MAXLEN))
		{
			throw new IllegalArgumentException("INVALID PayzippyTransactionSaleId");
		}
		// Validating refund amount
		else if (isNullOrEmpty(params.get(Constants.REFUND_AMOUNT))
		        || !NumberUtils.isDigits(params.get(Constants.REFUND_AMOUNT).toString()))
		{
			throw new IllegalArgumentException("INVALID REFIND_AMOUNT");
		}

		// Validating hash method
		else if (ValidityCheck.isNullOrEmpty(params.get(Constants.HASH_METHOD))
		        || !Constants.hashMethodRequirements.contains(params.get(Constants.HASH_METHOD)))
		{
			throw new IllegalArgumentException("INVALID HASH METHOD, MD5 and SHA256 only (must be in uppercase)");
		}
		// Optional Parameters

		// validating refund_reason
		else if (params.containsKey(Constants.REFUND_REASON)
		        && params.get(Constants.REFUND_REASON).toString().length() > Constants.REFUND_REASON_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID REFUND_REASON");
		}
		// validating refunded_by
		else if (params.containsKey(Constants.REFUNDED_BY)
		        && params.get(Constants.REFUNDED_BY).toString().length() > Constants.REFUNDED_BY_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID REFUNDED_BY");
		}
		// validating timegmt
		else if (params.containsKey(Constants.TIMEGMT)
		        && params.get(Constants.TIMEGMT).toString().length() != Constants.TIMEGMT_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID TIMEGMT , allowed in milliseconds");
		}

		// validating udf1
		else if (params.containsKey(Constants.UDF1)
		        && params.get(Constants.UDF1).toString().length() > Constants.UDF1_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF1");
		}

		// validating udf2
		else if (params.containsKey(Constants.UDF2)
		        && params.get(Constants.UDF2).toString().length() > Constants.UDF2_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF2");
		}

		// validating udf3
		else if (params.containsKey(Constants.UDF3)
		        && params.get(Constants.UDF3).toString().length() > Constants.UDF3_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF3");
		}

		// validating udf4
		else if (params.containsKey(Constants.UDF4)
		        && params.get(Constants.UDF4).toString().length() > Constants.UDF4_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF4");
		}

		// validating udf5
		else if (params.containsKey(Constants.UDF5)
		        && params.get(Constants.UDF5).toString().length() > Constants.UDF5_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF5");
		}

		return true;
	}

	public static boolean validateChargeParams(Map<String, Object> params) throws Exception
	{
		// Mandatory Parameters

		// Validating merchant id
		if (isNullOrEmpty(params.get(Constants.MERCHANT_ID))
		        || params.get(Constants.MERCHANT_ID).toString().length() > Constants.MERCHANT_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT ID");
		}
		// Validating buyers email address
		else if (isNullOrEmpty(params.get(Constants.BUYER_EMAIL_ADDRESS))
		        || params.get(Constants.BUYER_EMAIL_ADDRESS).toString().length() > Constants.BUYER_EMAIL_ADDRESS_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BUYER EMAIL ADDRESS");
		}
		// Validating Merchant transaction id
		else if (isNullOrEmpty(params.get(Constants.MERCHANT_TRANSACTION_ID))
		        || params.get(Constants.MERCHANT_TRANSACTION_ID).toString().length() > Constants.MERCHANT_TRANSACTION_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT TRANSACTION ID");
		}

		// Validating transaction amount
		else if (isNullOrEmpty(params.get(Constants.TRANSACTION_AMOUNT))
		        || !NumberUtils.isDigits(params.get(Constants.TRANSACTION_AMOUNT).toString()))
		{
			throw new IllegalArgumentException("INVALID TRANSACTION AMOUNT");
		}
		// Validating payment method
		else if (isNullOrEmpty(params.get(Constants.PAYMENT_METHOD))
		        || !Constants.validPaymentMethods.contains(params.get(Constants.PAYMENT_METHOD)))
		{
			throw new IllegalArgumentException("INVALID PAYMENT METHOD");
		}
		// Validating bank name
		else if (Constants.bankNameRequirement.contains(params.get(Constants.PAYMENT_METHOD))
		        && (isNullOrEmpty(params.get(Constants.BANK_NAME))))
		{
			throw new IllegalArgumentException("BANK NAME NOT SET");
		}

		// Validating EMI value
		else if ("emi".equalsIgnoreCase(params.get(Constants.PAYMENT_METHOD).toString())
		        && (isNullOrEmpty(params.get(Constants.EMI_MONTHS))
		                || !NumberUtils.isDigits(params.get(Constants.EMI_MONTHS).toString()) || Integer
		                .parseInt(params.get(Constants.EMI_MONTHS).toString()) <= 0))
		{
			throw new IllegalArgumentException("INVALID EMI MONTHS");
		}

		// validating CARD_CAPTURE value
		else if (Constants.CARD_CAPTURE.equalsIgnoreCase(params.get(Constants.PAYMENT_METHOD).toString())
		        && (isNullOrEmpty(params.get(Constants.CARD_NUMBER))))
		{
			throw new IllegalArgumentException("INVALID CARD NUMBER FOR CARD CAPTURE PAYMENT METHOD");
		}

		else if (Constants.CARD_CAPTURE.equalsIgnoreCase(params.get(Constants.PAYMENT_METHOD).toString())
		        && (isNullOrEmpty(params.get(Constants.EXPIRY_MONTH))))
		{
			throw new IllegalArgumentException("INVALID EXPIRY MONTH FOR CARD CAPTURE PAYMENT METHOD");
		}

		else if (Constants.CARD_CAPTURE.equalsIgnoreCase(params.get(Constants.PAYMENT_METHOD).toString())
		        && (isNullOrEmpty(params.get(Constants.EXPIRY_YEAR))))
		{
			throw new IllegalArgumentException("INVALID EXPIRY YEAR FOR CARD CAPTURE PAYMENT METHOD");
		}

		// Validating currency
		else if (isNullOrEmpty(params.get(Constants.CURRENCY)))
		{
			throw new IllegalArgumentException("CURRENCY NOT SET");
		}

		// Validating UI mode
		else if (isNullOrEmpty(params.get(Constants.UI_MODE))
		        || !Constants.uiModeRequirements.contains(params.get(Constants.UI_MODE)))
		{
			throw new IllegalArgumentException("INVALID UI MODE");
		}
		// Validating hash method
		else if (isNullOrEmpty(params.get(Constants.HASH_METHOD))
		        || !Constants.hashMethodRequirements.contains(params.get(Constants.HASH_METHOD)))
		{
			throw new IllegalArgumentException("INVALID HASH METHOD, MD5 and SHA256 only (must be in uppercase)");
		}
		// Validating merchant key id
		else if (isNullOrEmpty(params.get(Constants.MERCHANT_KEY_ID))
		        || params.get(Constants.MERCHANT_KEY_ID).toString().length() > Constants.MERCHANT_KEY_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT KEY ID");
		}
		// Validating transaction_type
		else if (isNullOrEmpty(params.get(Constants.TRANSACTION_TYPE))
		        || !"SALE".equals(params.get(Constants.TRANSACTION_TYPE)))
		{
			throw new IllegalArgumentException("INVALID MERCHANT TRANSACTION TYPE");
		}

		// -------------------------- Optional Parameters ------------------------------

		// validating buyer_phone_no
		else if (params.containsKey(Constants.BUYER_PHONE_NO)
		        && params.get(Constants.BUYER_PHONE_NO).toString().length() > Constants.BUYER_PHONE_NO_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BUYER_PHONE_NO");
		}
		// validating buyer_unique_id
		else if (params.containsKey(Constants.BUYER_UNIQUE_ID)
		        && params.get(Constants.BUYER_UNIQUE_ID).toString().length() > Constants.BUYER_UNIQUE_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BUYER_UNIQUE_ID");
		}
		// validating shipping_address
		else if (params.containsKey(Constants.SHIPPING_ADDRESS)
		        && params.get(Constants.SHIPPING_ADDRESS).toString().length() > Constants.SHIPPING_ADDRESS_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID SHIPPING ADDRESS");
		}
		/*
		 * validating shipping_city
		 * else if (params.containsKey(Constants.SHIPPING_CITY)
		 * && params.get(Constants.SHIPPING_CITY).toString().length() > Constants.SHIPPING_CITY_MAXLEN)
		 * {
		 * throw new IllegalArgumentException("INVALID SHIPPING_CITY");
		 * }
		 * // validating shipping_state
		 * else if (params.containsKey(Constants.SHIPPING_STATE)
		 * && params.get(Constants.SHIPPING_STATE).toString().length() > Constants.SHIPPING_STATE_MAXLEN)
		 * {
		 * throw new IllegalArgumentException("INVALID SHIPPING_STATE");
		 * }
		 */
		// validating shipping_zip
		else if (params.containsKey(Constants.SHIPPING_ZIP)
		        && params.get(Constants.SHIPPING_ZIP).toString().length() > Constants.SHIPPING_ZIP_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID SHIPPING_ZIP");
		}
		// validating shipping_country
		else if (params.containsKey(Constants.SHIPPING_COUNTRY)
		        && params.get(Constants.SHIPPING_COUNTRY).toString().length() > Constants.SHIPPING_COUNTRY_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID SHIPPING_COUNTRY");
		}
		// validating source
		else if (params.containsKey(Constants.SOURCE)
		        && params.get(Constants.SOURCE).toString().length() > Constants.SOURCE_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID SOURCE");
		}
		// validating callback_url
		else if (params.containsKey(Constants.CALLBACK_URL)
		        && params.get(Constants.CALLBACK_URL).toString().length() > Constants.CALLBACK_URL_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID CALLBACK_URL");
		}
		// validating billing_name
		else if (params.containsKey(Constants.BILLING_NAME)
		        && params.get(Constants.BILLING_NAME).toString().length() > Constants.BILLING_NAME_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BILLING_NAME");
		}
		// validating billing_address
		else if (params.containsKey(Constants.BILLING_ADDRESS)
		        && params.get(Constants.BILLING_ADDRESS).toString().length() > Constants.BILLING_ADDRESS_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BILLING_ADDRESS");
		}
		/*
		 * validating billing_city
		 * else if (params.containsKey(Constants.BILLING_CITY)
		 * && params.get(Constants.BILLING_CITY).toString().length() > Constants.BILLING_CITY_MAXLEN)
		 * {
		 * throw new IllegalArgumentException("INVALID BILLING_CITY");
		 * }
		 * // validating billing_state
		 * else if (params.containsKey(Constants.BILLING_STATE)
		 * && params.get(Constants.BILLING_STATE).toString().length() > Constants.BILLING_STATE_MAXLEN)
		 * {
		 * throw new IllegalArgumentException("INVALID BILLING_STATE");
		 * }
		 */
		// validating billing_zip
		else if (params.containsKey(Constants.BILLING_ZIP)
		        && params.get(Constants.BILLING_ZIP).toString().length() > Constants.BILLING_ZIP_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BILLING_ZIP");
		}
		// validating billing_country
		else if (params.containsKey(Constants.BILLING_COUNTRY)
		        && params.get(Constants.BILLING_COUNTRY).toString().length() > Constants.BILLING_COUNTRY_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID BILLING_COUNTRY");
		}
		// validating min_sla
		else if (params.containsKey(Constants.MIN_SLA)
		        && (params.get(Constants.MIN_SLA).toString().length() > Constants.MIN_SLA_MAXLEN || !NumberUtils
		                .isDigits(params.get(Constants.MIN_SLA).toString())))
		{
			throw new IllegalArgumentException("INVALID MIN_SLA");
		}
		// validating is_user_logged_in
		else if (params.containsKey(Constants.IS_USER_LOGGED_IN)
		        && !(params.get(Constants.IS_USER_LOGGED_IN).toString().equalsIgnoreCase("true") || params
		                .get(Constants.IS_USER_LOGGED_IN).toString().equalsIgnoreCase("false")))
		{
			throw new IllegalArgumentException("INVALID IS_USER_LOGGED_IN");
		}

		// validating address_count
		else if (params.containsKey(Constants.ADDRESS_COUNT)
		        && (params.get(Constants.ADDRESS_COUNT).toString().length() > Constants.ADDRESS_COUNT_MAXLEN || !NumberUtils
		                .isDigits(params.get(Constants.ADDRESS_COUNT).toString())))
		{
			throw new IllegalArgumentException("INVALID ADDRESS_COUNT");
		}

		// validating sales_channel
		else if (params.containsKey(Constants.SALES_CHANNEL)
		        && params.get(Constants.SALES_CHANNEL).toString().length() > Constants.SALES_CHANNEL_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID SALES_CHANNEL");
		}

		// validating item_total
		else if (params.containsKey(Constants.ITEM_TOTAL)
		        && params.get(Constants.ITEM_TOTAL).toString().length() > Constants.ITEM_TOTAL_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID ITEM_TOTAL");
		}

		// validating item_vertical
		else if (params.containsKey(Constants.ITEM_VERTICAL)
		        && params.get(Constants.ITEM_VERTICAL).toString().length() > Constants.ITEM_VERTICAL_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID ITEM_VERTICAL");
		}

		// validating min_sla
		else if (params.containsKey(Constants.MIN_SLA)
		        && (params.get(Constants.MIN_SLA).toString().length() > Constants.MIN_SLA_MAXLEN || !NumberUtils
		                .isDigits(params.get(Constants.MIN_SLA).toString())))
		{
			throw new IllegalArgumentException("INVALID MIN_SLA");
		}

		// validating timegmt
		else if (params.containsKey(Constants.TIMEGMT)
		        && params.get(Constants.TIMEGMT).toString().length() != Constants.TIMEGMT_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID TIMEGMT, allowed in milliseconds");
		}

		// validating udf1
		else if (params.containsKey(Constants.UDF1)
		        && params.get(Constants.UDF1).toString().length() > Constants.UDF1_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF1");
		}

		// validating udf2
		else if (params.containsKey(Constants.UDF2)
		        && params.get(Constants.UDF2).toString().length() > Constants.UDF2_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF2");
		}

		// validating udf3
		else if (params.containsKey(Constants.UDF3)
		        && params.get(Constants.UDF3).toString().length() > Constants.UDF3_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF3");
		}

		// validating udf4
		else if (params.containsKey(Constants.UDF4)
		        && params.get(Constants.UDF4).toString().length() > Constants.UDF4_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF4");
		}

		// validating udf5
		else if (params.containsKey(Constants.UDF5)
		        && params.get(Constants.UDF5).toString().length() > Constants.UDF5_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID UDF5");
		}

		return true;

	}

	public static boolean validateQueryParams(Map<String, Object> params) throws Exception
	{
		// Validating merchant id
		if (isNullOrEmpty(params.get(Constants.MERCHANT_ID))
		        || params.get(Constants.MERCHANT_ID).toString().length() > Constants.MERCHANT_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT_ID");
		}
		// Validating merchant key id
		else if (isNullOrEmpty(params.get(Constants.MERCHANT_KEY_ID))
		        || params.get(Constants.MERCHANT_KEY_ID).toString().length() > Constants.MERCHANT_KEY_ID_MAXLEN)
		{
			throw new IllegalArgumentException("INVALID MERCHANT KEY ID");
		}
		// Validating merchant transaction id
		else if (!params.containsKey(Constants.PAYZIPPY_TRANSACTION_ID)
		        && (isNullOrEmpty(params.get(Constants.MERCHANT_TRANSACTION_ID)) || params
		                .get(Constants.MERCHANT_TRANSACTION_ID).toString().length() > Constants.PAYZIPPY_TRANSACTION_ID_MAXLEN))
		{
			throw new IllegalArgumentException("INVALID MERCHANT TRANSACTION ID");
		}
		// Validating payzippy_sale_transaction_id
		else if (!params.containsKey(Constants.MERCHANT_TRANSACTION_ID)
		        && (isNullOrEmpty(params.get(Constants.PAYZIPPY_TRANSACTION_ID)) || params
		                .get(Constants.PAYZIPPY_TRANSACTION_ID).toString().length() > Constants.MERCHANT_TRANSACTION_ID_MAXLEN))
		{
			throw new IllegalArgumentException("INVALID PAYZIPPY_TRANSACTION_ID");
		}

		// Validating hash method
		else if (isNullOrEmpty(params.get(Constants.HASH_METHOD))
		        || !Constants.hashMethodRequirements.contains(params.get(Constants.HASH_METHOD)))
		{
			throw new IllegalArgumentException("INVALID HASH METHOD, MD5 and SHA256 only (must be in uppercase)");
		}
		// Optional
		// Validating transaction type
		else if (params.containsKey(Constants.TRANSACTION_TYPE)
		        && (isNullOrEmpty(params.get(Constants.TRANSACTION_TYPE)) || !Arrays.asList("SALE", "REFUND").contains(
		                params.get(Constants.TRANSACTION_TYPE).toString())))
		{
			throw new IllegalArgumentException("INVALID TRANSACTION_TYPE");
		}
		return true;
	}

}
