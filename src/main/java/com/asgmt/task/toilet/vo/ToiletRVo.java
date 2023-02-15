package com.asgmt.task.toilet.vo;

import lombok.Data;

@Data
public class ToiletRVo {
	private String publfacltDivNm; /* 구분 */
	private String pbctltPlcNm; /* 화장실명 */
	private String refineRoadnmAddr; /* 소재지도로명주소 */
	private String refineLotnoAddr; /* 소재지지번주소 */
	private String maleFemaleCmnuseToiletYn; /* 남녀공용화장실여부 */
	private String maleWtrclsCnt; /* 남성용-대변기수 */
	private String maleUilCnt; /* 남성용-소변기수 */
	private String maleDspsnWtrclsCnt; /* 남성용-장애인용대변기수 */
	private String maleDspsnUilCnt; /* 남성용-장애인용소변기수 */
	private String maleKidWtrclsCnt; /* 남성용-어린이용대변기수 */
	private String maleKidUilCnt; /* 남성용-어린이용소변기수 */
	private String femaleWtrclsCnt; /* 여성용-대변기수 */
	private String femaleDspsnWtrclsCnt; /* 여성용-장애인용대변기수 */
	private String femaleKidWtrclsCnt; /* 여성용-어린이용대변기수 */
	private String mnginstNm; /* 관리기관명 */
	private String mnginstTelno; /* 전화번호 */
	private String openTmInfo; /* 개방시간 */
	private String instlYm; /* 설치연월 */
	private String refineWgs84Lat; /* 위도 */
	private String refineWgs84Logt; /* 경도 */
	private String toiletPosesnDiv; /* 화장실소유구분 */
	private String toiletInstlPlcType; /* 화장실설치장소유형 */
	private String procCont; /* 오물처리방식 */
	private String embelInstlYn; /* 비상벨설치여부 */
	private String embelInstlPlc; /* 비상벨설치장소 */
	private String toiletYn; /* 화장실여부 */
	private String instlYn; /* 설치여부 */
	private String plcNm; /* 기저귀교환대장소 */
	private String reBuildYm; /* 리모델링연월 */
	private String dataStdDe; /* 데이터기준일자 */
}
