package com.asgmt.task.coin.utils;

import java.util.List;
import java.util.Map;

import com.asgmt.task.coin.vo.SuggestionCoinRVo;

public class CandlesAnalyzeIncrease {

	/**
	 *
	 * @see 해머형 / 역해머형
	 * @param coinList
	 * @param time
	 * @param coinInfo
	 * @return
	 */
	public static SuggestionCoinRVo Hammer(List<Map<String, String>> coinList, String time, Map<String, String> coinInfo) {
		SuggestionCoinRVo result = null;
		String candleName = "";

		if( coinList.size() < 5) return result;

		Map<String, String> one = coinList.get(0);
		Map<String, String> two = coinList.get(1);
		Map<String, String> three = coinList.get(2);
		Map<String, String> four = coinList.get(3);
		//Map<String, String> five = coinList.get(4);

		// 하락 -> 시가가 종가보다 크다!
		double four_opening_price 	= Double.parseDouble(String.valueOf(four.get("opening_price"))); // 4 캔들 시가
		double four_trade_price 	= Double.parseDouble(String.valueOf(four.get("trade_price"))); // 4 캔들 종가
		double three_opening_price 	= Double.parseDouble(String.valueOf(three.get("opening_price"))); // 3 캔들 시가
		double three_trade_price 	= Double.parseDouble(String.valueOf(three.get("trade_price"))); // 3 캔들 종가
		double one_trade_price 		= Double.parseDouble(String.valueOf(one.get("trade_price")));// 최근캔들 종가
		double two_opening_price 	= Double.parseDouble(String.valueOf(two.get("opening_price"))); // 시가
		double two_trade_price 		= Double.parseDouble(String.valueOf(two.get("trade_price"))); // 종가
		double two_low_price 		= Double.parseDouble(String.valueOf(two.get("low_price"))); // 저가
		double two_high_price 		= Double.parseDouble(String.valueOf(two.get("high_price"))); // 고가

		String callTime = one.get("candle_date_time_kst");

		if (two_opening_price < two_trade_price && two_low_price < two_opening_price) {

			if (
				two_trade_price - two_opening_price <
				two_opening_price - two_low_price
			) {
				if ((four_opening_price > four_trade_price) && (three_opening_price > three_trade_price)) {
					if (two_trade_price < one_trade_price) {
						result = new SuggestionCoinRVo();
						if ((two_high_price - two_trade_price) < (two_low_price - two_trade_price)) {
							// 망치
							candleName = "hammer";
							result.setCoinEnName(one.get("market"));
							result.setPrice(String.valueOf(one.get("trade_price")));
							result.setTrends("BUY");
							result.setTimeDivisoin(time);
							result.setCandleName(candleName);
							result.setCoinEnName(coinInfo.get("english_name"));
							result.setCoinKoName(coinInfo.get("korean_name"));
							result.setCallTime(callTime);
						} else {
							// 역망치
							candleName = "inversehammer";
							result.setCoinEnName(one.get("market"));
							result.setPrice(String.valueOf(one.get("trade_price")));
							result.setTrends("BUY");
							result.setTimeDivisoin(time);
							result.setCandleName(candleName);
							result.setCoinEnName(coinInfo.get("english_name"));
							result.setCoinKoName(coinInfo.get("korean_name"));
							result.setCallTime(callTime);
						}

					}
				}
			}
		}

		return result;
	}

	/**
	 *
	 * @see 상승장악형
	 * @param coinList
	 * @param time
	 * @param coinInfo
	 * @return
	 */
	public static SuggestionCoinRVo bullishEngulfing(List<Map<String, String>> coinList, String time, Map<String, String> coinInfo) {
		SuggestionCoinRVo result = null;

		if( coinList.size() < 5) return result;

		String candleName = "bullishEngulfing";
		Map<String, String> one = coinList.get(0);
		Map<String, String> two = coinList.get(1);
		Map<String, String> three = coinList.get(2);
		Map<String, String> four = coinList.get(3);
		//Map<String, String> five = coinList.get(4);

		// 하락 -> 시가가 종가보다 크다!
		double four_opening_price 	= Double.parseDouble(String.valueOf(four.get("opening_price"))); // 4 캔들 시가
		double four_trade_price 	= Double.parseDouble(String.valueOf(four.get("trade_price"))); // 4 캔들 종가

		double three_opening_price 	= Double.parseDouble(String.valueOf(three.get("opening_price"))); // 3 캔들 시가
		double three_trade_price 	= Double.parseDouble(String.valueOf(three.get("trade_price"))); // 3 캔들 종가
		double three_low_price 		= Double.parseDouble(String.valueOf(three.get("low_price"))); // 3 캔들 저가
		double three_high_price 	= Double.parseDouble(String.valueOf(three.get("high_price"))); // 3 캔들 고가

		double two_opening_price 	= Double.parseDouble(String.valueOf(two.get("opening_price"))); // 시가
		double two_trade_price 		= Double.parseDouble(String.valueOf(two.get("trade_price"))); // 종가
		double two_low_price 		= Double.parseDouble(String.valueOf(two.get("low_price"))); // 저가
		double two_high_price 		= Double.parseDouble(String.valueOf(two.get("high_price"))); // 고가

		double one_trade_price 		= Double.parseDouble(String.valueOf(one.get("trade_price")));// 최근캔들 종가

		String callTime = one.get("candle_date_time_kst");

		// 4번 캔들 하락
		// 3번 캔들 하락
		// 2번 캔들 상승 2번캔들 이 3번 캔들을 감쌈
		// 1번 캔들 상승

		// 2캔들 상향 , 1캔들 상향
		if (two_opening_price < two_trade_price && two_low_price < two_opening_price && two_trade_price < one_trade_price) {

			// 3, 4 번 하락장
			if ((four_opening_price > four_trade_price) && (three_opening_price > three_trade_price)) {

				/**
				 *  1. 2번의 시가가 3번의 종가보다 크거나 같다
				 *  2. 3번의 고가가 2번의 종가보다 작다
				 */
				if (three_trade_price <= two_opening_price && three_high_price < two_trade_price) {
					result = new SuggestionCoinRVo();
					candleName = "bullishEngulfing";
					result.setCoinEnName(one.get("market"));
					result.setPrice(String.valueOf(one.get("trade_price")));
					result.setTrends("BUY");
					result.setTimeDivisoin(time);
					result.setCandleName(candleName);
					result.setCoinEnName(coinInfo.get("english_name"));
					result.setCoinKoName(coinInfo.get("korean_name"));
					result.setCallTime(callTime);
				}
			}
		}

		return result;
	}

	/**
	 *
	 * @see 모닝스타형
	 * @param coinList
	 * @param time
	 * @param coinInfo
	 * @return
	 */
	public static SuggestionCoinRVo morningStar(List<Map<String, String>> coinList, String time, Map<String, String> coinInfo) {
		SuggestionCoinRVo result = null;

		if( coinList.size() < 5) return result;

		String candleName = "morningStar";
		Map<String, String> one = coinList.get(0);
		Map<String, String> two = coinList.get(1);
		Map<String, String> three = coinList.get(2);
		Map<String, String> four = coinList.get(3);

		// 하락 -> 시가가 종가보다 크다!
		double four_opening_price 	= Double.parseDouble(String.valueOf(four.get("opening_price"))); 	// 4 캔들 시가
		double four_trade_price 	= Double.parseDouble(String.valueOf(four.get("trade_price"))); 		// 4 캔들 종가
		double four_low_price 		= Double.parseDouble(String.valueOf(four.get("low_price"))); 		// 4 캔들 저가
		double four_high_price 		= Double.parseDouble(String.valueOf(four.get("high_price"))); 		// 4 캔들 고가

		double three_opening_price 	= Double.parseDouble(String.valueOf(three.get("opening_price"))); 	// 3 캔들 시가
		double three_trade_price 	= Double.parseDouble(String.valueOf(three.get("trade_price"))); 	// 3 캔들 종가
		double three_low_price 		= Double.parseDouble(String.valueOf(three.get("low_price"))); 		// 3 캔들 저가
		double three_high_price 	= Double.parseDouble(String.valueOf(three.get("high_price"))); 		// 3 캔들 고가

		double two_opening_price 	= Double.parseDouble(String.valueOf(two.get("opening_price"))); 	// 시가
		double two_trade_price 		= Double.parseDouble(String.valueOf(two.get("trade_price"))); 		// 종가
		double two_low_price 		= Double.parseDouble(String.valueOf(two.get("low_price"))); 		// 저가
		double two_high_price 		= Double.parseDouble(String.valueOf(two.get("high_price"))); 		// 고가

		double one_opening_price 	= Double.parseDouble(String.valueOf(one.get("opening_price"))); 	// 시가
		double one_trade_price 		= Double.parseDouble(String.valueOf(one.get("trade_price"))); 		// 종가
		double one_low_price 		= Double.parseDouble(String.valueOf(one.get("low_price"))); 		// 저가
		double one_high_price 		= Double.parseDouble(String.valueOf(one.get("high_price"))); 		// 고가

		String callTime = one.get("candle_date_time_kst");

		// 4번 캔들 하락 긴 하락 -> 긴하락 어떻게?? 몇프로?? 8% 이상 급
		// 퍼센트 계산 하는법은
		// 시가가 종가보다 클경우 시가 - 종가
		// 차액 / 시가 * 100 > 8 일경우
		if (four_opening_price > four_trade_price && (((four_opening_price - four_trade_price) / four_opening_price) * 100) > 4 ) {

			// 3번 캔들의 시가 or 종가 가 4번 캔들의 종가보다 낮을경우
			if(four_trade_price > three_opening_price && four_trade_price > three_trade_price) {
				if (two_trade_price > two_opening_price) {
					result = new SuggestionCoinRVo();
					result.setCoinEnName(one.get("market"));
					result.setPrice(String.valueOf(one.get("trade_price")));
					result.setTrends("BUY");
					result.setTimeDivisoin(time);
					result.setCandleName(candleName);
					result.setCoinEnName(coinInfo.get("english_name"));
					result.setCoinKoName(coinInfo.get("korean_name"));
					result.setCallTime(callTime);
				}
			}
		}

		return result;
	}

}
