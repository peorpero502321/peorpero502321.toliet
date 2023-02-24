package com.asgmt.task.coin.vo;

import lombok.Data;

@Data
public class TransactionCoinPVo {

	private String userCode; 		// 유저정보
	private String timeDivisoin; 	// 데이터 조회 일시 구분
	private String coinEnName; 		// 코인 영문 명
	private String trends; 			// 추세 BUY, HOLD, SELL
	
}
