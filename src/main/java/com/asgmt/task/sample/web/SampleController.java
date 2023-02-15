package com.asgmt.task.sample.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asgmt.task.sample.service.SampleService;
import com.asgmt.task.sample.vo.SamplePVo;
import com.asgmt.task.utils.JsonStringConvert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Controller
public class SampleController {

	@Autowired
	private SampleService sample;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index() {
		System.out.println("인덱스 페이지 호출");
		return "index";
	}

	@RequestMapping(value="/table", method = RequestMethod.GET)
	public String getTable(SamplePVo paramVo,Model model) {

		paramVo.setTableNm("pub_toilet");

		System.out.println(sample.selectTable(paramVo));

		model.addAttribute("result", sample.selectTable(paramVo));

		return "sample/index";
	}

}
