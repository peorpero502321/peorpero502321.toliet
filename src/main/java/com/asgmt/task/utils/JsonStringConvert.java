package com.asgmt.task.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringConvert {

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> getListMap(String jsonList) throws JsonMappingException, JsonProcessingException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONArray jsonArr;

		try {
			jsonArr = new JSONArray(jsonList);
			Map<String, String> map = null;

			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				map = new ObjectMapper().readValue(jsonObj.toString(), Map.class);
				list.add(map);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
