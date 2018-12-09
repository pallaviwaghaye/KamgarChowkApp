package com.webakruti.kamgarchowk.utils.httpparsers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParserGETRequest {

	private InputStream is;
	private String result;
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	@SuppressWarnings("deprecation")
	public JSONObject makeRequestForJSONObject(String path,String header) {
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		@SuppressWarnings("deprecation")
		HttpGet request = new HttpGet(path);

		if (header != null) {
			request.setHeader("Authorization","Bearer " + header);
		}

		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
			int responseCode = response.getStatusLine().getStatusCode();

            if(responseCode == 200) {

                Log.i("response of get request", request.toString());
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                if (is != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                }

                jsonObject = new JSONObject(result);
            } else if(responseCode == 403) {
            }
		}
        catch (IOException e) {
			e.printStackTrace();
		}
        catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@SuppressWarnings("deprecation")
	public JSONArray makeRequestForJSONArray(String path) {
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		@SuppressWarnings("deprecation")
		HttpGet request = new HttpGet(path);

		/*if (autho_token != null) {
			request.setHeader("Authorization", autho_token);
		}
*/
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);

			Log.i("response of get request", request.toString());
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
			}

			jsonArray = new JSONArray(result);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

}
