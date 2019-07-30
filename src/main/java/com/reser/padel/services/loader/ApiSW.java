package com.reser.padel.services.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiSW {

	public ApiSW() {}
	
	public JsonObject getBuilder(String path) throws Exception {

		HttpGet httpGet = new HttpGet("https://swapi.co/api/" + path + "/");
		httpGet.addHeader("accept", "application/json");
		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpResponse response = httpClient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(stringBuilder.toString(), JsonObject.class);
		
		bufferedReader.close();

		return jsonObject;
	}

}
