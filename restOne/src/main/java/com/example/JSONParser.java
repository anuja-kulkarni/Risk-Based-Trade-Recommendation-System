package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder(); //converts char to string
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString(); //returns json string
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream(); 
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      // The JSON received from Google has certain characters which don't make it a valid json.
	      // We are removing those characters.
	      String jsonText = readAll(rd).replace("//", "").replace("[", "").replace("]", "");
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } 
	    finally {
	      is.close();
	    }
	  }
	  
}
 