package com.asgmt.task.coin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asgmt.task.coin.service.CoinService;
import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;

@RestController
public class CoinApiController {

	@Autowired
	private CoinService coin;

	@RequestMapping(value="api/rcmndCoin", method = RequestMethod.GET)
	public List<SuggestionCoinRVo> selectRcmndCoin(SuggestionCoinPVo param) {
		List<SuggestionCoinRVo> result = coin.selectSuggestionCoin(param);

		return result;
	}
}
