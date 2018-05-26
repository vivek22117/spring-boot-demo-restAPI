package com.app.app.utility;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RestClient {

	public String get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient client =HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		
		System.out.println("Status code is: " + statusCode);
		
		String data = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		
		return data;
	}
}
