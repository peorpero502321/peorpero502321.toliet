package com.asgmt.task.http;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpApi {

	/**
	 * category {m ="마켓 조회", t="거래 조회" ,c ="캔들차트 조회" }
	 * m : 없음
	 * c : dateType, time, market, count
	 * t : market, count
	 * dateType {minutes, days, weeks, months}
	 * time		{1, 5, 10}
	 * market 	{KRW-BTC, }
	 * count	{1 ~ 90}
	 */
	public static String getJsonData(String category, String dateType, String time, String market, String count) {
		OkHttpClient client = new OkHttpClient();
		String marketUrl = "https://api.upbit.com/v1/market/all?isDetails=false";
		String tradesUrl = "https://api.upbit.com/v1/trades";
		String candlesUrl = "https://api.upbit.com/v1/candles";
		String url ="";
		String jsonLst ="";

		if (category.equals("c")) {
			if ("minutes".equals(dateType)) {
				url = candlesUrl + "/" + dateType + "/" + time + "?market=" + market + "&count=" + count;
			} else if ("days".equals(dateType)) {
				url = candlesUrl + "/" + dateType + "?market=" + market + "&count=" + count;
			} else if ("weeks".equals(dateType)) {
				url = candlesUrl + "/" + dateType + "?market=" + market + "&count=" + count;
			} else if ("months".equals(dateType)) {
				url = candlesUrl + "/" + dateType + "?market=" + market + "&count=" + count;
			}

		} else if (category.equals("m")) {
			url = marketUrl;
		} else if (category.equals("t")) {
			url = tradesUrl + "/ticks?market=" + market + "&count=" + count;
		}

		/**
		 * dateType {minutes, days, weeks, months}
		 * time		{1, 5, 10}
		 * market 	{KRW-BTC, }
		 * count	{1 ~ 90}
		 */
		//System.out.println(market+ ":::: " + url);
		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .addHeader("accept", "application/json")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			jsonLst = response.body().string();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonLst;
	}

}
