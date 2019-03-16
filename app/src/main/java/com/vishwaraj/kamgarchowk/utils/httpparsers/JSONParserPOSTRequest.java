package com.vishwaraj.kamgarchowk.utils.httpparsers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("deprecation")
public class JSONParserPOSTRequest {

	private HttpResponse response;
	private InputStream is;
	private String result;
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	public JSONObject makeRequest(String path, String token) {

		try {
			//instantiates httpclient to make request
			@SuppressWarnings("deprecation")
			DefaultHttpClient httpclient = new DefaultHttpClient();

			//url with the post data
			@SuppressWarnings("deprecation")
			HttpPost httpost = new HttpPost(path);
			if (token != null) {
				httpost.setHeader("Authorization","Bearer " + token);
			}
			//passes the results to a string builder/entity


			//StringEntity se = new StringEntity(params.toString(),"UTF-8");

			//sets the post request as the resulting string
			//httpost.setEntity(se);
			//sets a request header so the page receving the request
			//will know what to do with it
			httpost.setHeader("Accept", "application/json");
			httpost.setHeader("Content-type", "application/json");
			response = httpclient.execute(httpost);
            int responseCode = response.getStatusLine().getStatusCode();

            if(responseCode == 200) {
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
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;

	}
	public JSONArray makeRequestForJsonArray(String path, String params) {

		try {
			//instantiates httpclient to make request
			@SuppressWarnings("deprecation")
			DefaultHttpClient httpclient = new DefaultHttpClient();

			//url with the post data
			@SuppressWarnings("deprecation")
			HttpPost httpost = new HttpPost(path);
//			if (token != null) {
//				httpost.setHeader("token", token);
//			}
			//passes the results to a string builder/entity
			@SuppressWarnings("deprecation")

			StringEntity se = new StringEntity(params.toString(),"UTF-8");

			//sets the post request as the resulting string
			httpost.setEntity(se);
			//sets a request header so the page receving the request
			//will know what to do with it
			httpost.setHeader("Accept", "application/json");
			httpost.setHeader("Content-type", "application/json");
			response = httpclient.execute(httpost);
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
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonArray;

	}
}
