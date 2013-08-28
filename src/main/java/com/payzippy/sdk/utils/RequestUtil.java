package com.payzippy.sdk.utils;

import java.net.URLEncoder;
import java.util.Map;

public class RequestUtil
{

	public static String getUrl(Map<String, Object> params, String baseUrl)
	{
		StringBuilder queryString = new StringBuilder();
		if (params == null || params.size() == 0)
		{
			return "";
		}

		for (Map.Entry<String, Object> e : params.entrySet())
		{
			if (e.getValue() != null)
			{
				try
				{
					queryString.append(URLEncoder.encode(e.getKey(), "UTF-8") + "=");
					queryString.append(URLEncoder.encode(e.getValue().toString(), "UTF-8") + "&");
				}
				catch (Exception exception)
				{ // Will never happen
					queryString.append("&");
					continue;
				}
			}
		}

		if (queryString.length() > 0)
		{
			queryString.deleteCharAt(queryString.length() - 1);
		}

		return baseUrl + "?" + queryString.toString();
	}

}
