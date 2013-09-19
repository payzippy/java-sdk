package com.payzippy.sdk.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

public enum WebClient
{
	INSTANCE;
	private final HttpClient client;

	WebClient()
	{
		client = new DefaultHttpClient();

	}

	public String doGet(Map<String, Object> params, String baseUrl) throws Exception
	{
		HttpGet request = new HttpGet(RequestUtil.getUrl(params, baseUrl));
		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String responseText = "";
		String line = "";
		while ((line = rd.readLine()) != null)
		{
			responseText += line;
		}

		return responseText;

	}

	public String doPost(Map<String, Object> params, String baseUrl) throws Exception
	{
		HttpPost post = new HttpPost(baseUrl);
		List<NameValuePair> entityParams = new ArrayList<NameValuePair>();
		for (String key : params.keySet())
		{
			entityParams.add(new BasicNameValuePair(key, params.get(key).toString()));
		}

		post.setEntity(new UrlEncodedFormEntity(entityParams));
		HttpResponse response = client.execute(post);

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String responseText = "";
		String line = "";
		while ((line = rd.readLine()) != null)
		{
			responseText += line;
		}

		return responseText;
	}

	public Map<String, Object> doQuery(Map<String, Object> params, String baseUrl) throws Exception
	{
		return (new ObjectMapper()).readValue(doPost(params, baseUrl), Map.class);
	}

	public Map<String, Object> doRefund(Map<String, Object> params, String baseUrl) throws Exception
	{
		return (new ObjectMapper()).readValue(doPost(params, baseUrl), Map.class);
	}

}
