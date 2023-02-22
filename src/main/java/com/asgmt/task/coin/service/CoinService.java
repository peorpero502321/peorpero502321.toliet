package com.asgmt.task.coin.service;

import java.util.List;
import java.util.Map;

import com.asgmt.task.coin.vo.CoinParamVo;
import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;

public interface CoinService {

	public List<Map<String, String>> selectCoinList(CoinParamVo param);

	public List<SuggestionCoinRVo> selectSuggestionCoin(SuggestionCoinPVo param);


}
