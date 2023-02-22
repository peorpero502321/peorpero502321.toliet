package com.asgmt.task.coin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asgmt.task.coin.dao.CoinDao;
import com.asgmt.task.coin.service.CoinService;
import com.asgmt.task.coin.utils.CandlesAnalyze;
import com.asgmt.task.coin.vo.CoinParamVo;
import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;
import com.asgmt.task.http.OkHttpApi;
import com.asgmt.task.utils.JsonStringConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	private CoinDao coin;

	public static List<SuggestionCoinRVo> lst = new ArrayList<SuggestionCoinRVo>();
	SimpleDateFormat timeFormet = new SimpleDateFormat( "yyyy년 MM월dd일 HH시mm분ss초");

	@Scheduled(cron = "0 0/15 * * * *")	// 15분마다
	public void test1() throws Exception {
		CoinParamVo param = new CoinParamVo();

		String value ="15";
		param.setCategory("m");
		param.setDateType("minutes");
		param.setTime("15");
		param.setCount("5");

		Date nowDate = new Date();
		String nowTime = timeFormet.format(nowDate);
		System.out.println("timeSet : "+  value +" min "+" : "+ nowTime);
		coin.deleteRcmndCoin(value);
		selectCoinList(param);

	}

	@Scheduled(cron = "0 0 0/1 * * *")	// 60분마다
	public void test2() throws Exception {
		CoinParamVo param = new CoinParamVo();
		String value ="60";
		param.setCategory("m");
		param.setDateType("minutes");
		param.setTime("60");
		param.setCount("5");

		Date nowDate = new Date();
		String nowTime = timeFormet.format(nowDate);
		System.out.println("timeSet : "+  value +" min "+" : "+ nowTime);
		coin.deleteRcmndCoin(value);
		selectCoinList(param);
	}

	@Scheduled(cron = "0 0 0/6 * * *")	// 240분마다
	public void test3() throws Exception {
		CoinParamVo param = new CoinParamVo();
		String value ="240";
		param.setCategory("m");
		param.setDateType("minutes");
		param.setTime("240");
		param.setCount("5");

		Date nowDate = new Date();
		String nowTime = timeFormet.format(nowDate);
		System.out.println("timeSet : "+  value +" min "+" : "+ nowTime);
		coin.deleteRcmndCoin(value);
		selectCoinList(param);
	}

	@Override
	public List<SuggestionCoinRVo> selectSuggestionCoin(SuggestionCoinPVo param) {
		List<SuggestionCoinRVo> result = null;
		result = coin.selectSuggestionCoin(param);
		if (result.size() < 1) {
			result  = null;
		}
		return result;
	}

	@Override
	public List<Map<String, String>> selectCoinList(CoinParamVo param) {

		 /* category {m ="마켓 조회", t="거래 조회" ,c ="캔들차트 조회" }
		 * m : 없음
		 * c : dateType, time, market, count
		 * t : market, count
		 * dateType {minutes, days, weeks, months}
		 * time		{1, 5, 10}
		 * market 	{KRW-BTC, }
		 * count	{1 ~ 90}
		 * */

		String marketJson = OkHttpApi.getJsonData("m", null,  null,  null,  null);
		List<Map<String, String>> list = null;
		lst = new ArrayList<SuggestionCoinRVo>();
		try {

			list = JsonStringConvert.getListMap(marketJson);
			try {
				for (Map<String, String> obj :list) {

					String marketName = obj.get("market");

					// param maketName 넣기
					param.setMarket(obj.get("market"));
					SuggestionCoinRVo coinVo = new SuggestionCoinRVo();
					if (marketName.contains("KRW")) {
						Thread.sleep(100);
						List<Map<String, String>> coinList = selectCoin(param);

						coinVo = CandlesAnalyze.Hammer(coinList, param.getTime(), obj);
						if (coinVo != null) {
							lst.add(coinVo);
						}
						coinVo = CandlesAnalyze.bullishEngulfing(coinList, param.getTime(), obj);
						if (coinVo != null) {
							lst.add(coinVo);
						}

						coinVo = CandlesAnalyze.morningStar(coinList, param.getTime(), obj);
						if (coinVo != null) {
							lst.add(coinVo);
						}
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lst);
		if (lst.size() > 0) {
			coin.inserRcmndCoin(lst);
		}

		return list;
	}

	public List<Map<String, String>> selectCoin(CoinParamVo param) {

		String coinJson = OkHttpApi.getJsonData("c", param.getDateType(), param.getTime(), param.getMarket(), param.getCount());
		List<Map<String, String>> list = null;
		try {
			list = JsonStringConvert.getListMap(coinJson);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
