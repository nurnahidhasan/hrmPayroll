package com.bracu.hrm.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class JSONUtil {
	
	public static <T> String getJsonObject(T object) {
        String resultJson = "";
        try {
            Gson gson = new Gson();
            resultJson = gson.toJson(object );
        }catch (JsonIOException e){
            System.out.println(e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultJson;
    }
}
