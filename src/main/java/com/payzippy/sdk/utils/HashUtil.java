package com.payzippy.sdk.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil
{
	public static String generateHash(Map<String, Object> params, String secretKey, String hashMode)
	{
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder strb = new StringBuilder(1000);
		for (String sk : keys)
		{
			String value = String.valueOf(params.get(sk));
			if (value != null && !"hash".equals(sk))
				strb.append(value + "|");
		}
		strb.append(secretKey);
		if ("md5".equalsIgnoreCase(hashMode))
		{
			return DigestUtils.md5Hex(strb.toString());
		}
		else if ("sha256".equalsIgnoreCase(hashMode))
		{
			return DigestUtils.sha256Hex(strb.toString()).toString();
		}
		return null;
	}

}
