package com.asgmt.task.coin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.asgmt.task.coin.vo.SuggestionCoinPVo;
import com.asgmt.task.coin.vo.SuggestionCoinRVo;

@Mapper
@Repository
public interface CoinDao {

	public List<SuggestionCoinRVo> selectSuggestionCoin(SuggestionCoinPVo param);

}
