package com.asgmt.task.coin.vo;

import lombok.Data;

@Data
public class TransactionCoinRVo {

	private String timeDivisoin; 	// 데이터 조회 일시 구분
	private String coinEnName; 		// 코인 영문 명
	private String coinKoName; 		// 코인 한글명
	private String candleName;		// 캔들 알고리즘 명 Hammer, ...
	private String trends; 			// 추세 BUY, HOLD, SELL
	private String price; 			// 가격
	private String callTime;		// 조회시간
}
