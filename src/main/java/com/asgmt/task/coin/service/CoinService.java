package com.asgmt.task.coin.service;

import java.util.List;

import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;

public interface CoinService {
	public List<SuggestionCoinRVo> selectSuggestionCoin(SuggestionCoinPVo param);


}
