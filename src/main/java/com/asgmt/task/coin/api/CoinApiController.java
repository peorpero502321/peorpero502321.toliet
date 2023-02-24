package com.asgmt.task.coin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asgmt.task.coin.service.CoinService;
import com.asgmt.task.coin.vo.CoinParamVo;
import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CoinApiController {

	@Autowired
	private CoinService coin;

	// 5분에 한번씩 반복 -> 대략 114개 * 5개 570개 데이터 반복은 114번 easy!
	@RequestMapping(value="/coin", method = RequestMethod.GET)
	public String getCoinInfo(CoinParamVo param) {
		System.out.println(new Gson().toJson(param));
		coin.selectCoinList(param);

	return "sample/index";
	}

	@RequestMapping(value="api/rcmndCoin", method = RequestMethod.GET)
	public List<SuggestionCoinRVo> selectRcmndCoin(SuggestionCoinPVo param) {
		List<SuggestionCoinRVo> result = coin.selectSuggestionCoin(param);

		return result;
	}
}
