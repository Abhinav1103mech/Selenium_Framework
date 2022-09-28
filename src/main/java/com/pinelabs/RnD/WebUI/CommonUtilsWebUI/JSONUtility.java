package com.pinelabs.RnD.WebUI.CommonUtilsWebUI;

import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class JSONUtility {

    public static String getValueFromJson(String filePath, String key) {
        String jsonTxt = null;
        File f = new File(filePath);
        if (f.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                jsonTxt = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
          //  System.out.println(jsonTxt);
        }
        fetchJSONValueFromKey(jsonTxt,key);
        return jsonTxt;
    }


    private static String fetchJSONValueFromKey(String jsonReq, String key) {

        JSONObject json = new JSONObject(jsonReq);
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        String val = "";
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        return fetchJSONValueFromKey(json.getJSONObject(nextKeys).toString(), key);
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        int i = 0;
                        if (i < jsonArray.length()) do {
                            String jsonArrayString = jsonArray.get(i).toString();
                            json = new JSONObject(jsonArrayString);
                            return fetchJSONValueFromKey(json.toString(), key);
                        } while (i < jsonArray.length());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            val = json.get(key).toString();
        }
        System.out.println("Value is " + val);
        return val;
    }
}

