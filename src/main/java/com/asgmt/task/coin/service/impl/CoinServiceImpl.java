package com.asgmt.task.coin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asgmt.task.coin.dao.CoinDao;
import com.asgmt.task.coin.service.CoinService;
import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	private CoinDao coin;

	@Override
	public List<SuggestionCoinRVo> selectSuggestionCoin(SuggestionCoinPVo param) {
		List<SuggestionCoinRVo> result = null;
		result = coin.selectSuggestionCoin(param);
		if (result.size() < 1) {
			result  = null;
		}
		return result;
	}

}
