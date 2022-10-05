package com.asgmt.task.toilet.vo;

import lombok.Data;

@Data
public class ToiletDtlRVo {
	private String PUBLFACLT_DIV_NM; /* 구분 */
	private String PBCTLT_PLC_NM; /* 화장실명 */
	private String REFINE_ROADNM_ADDR; /* 소재지도로명주소 */
	private String REFINE_LOTNO_ADDR; /* 소재지지번주소 */
	private String MALE_FEMALE_CMNUSE_TOILET_YN; /* 남녀공용화장실여부 */
	private String MALE_WTRCLS_CNT; /* 남성용-대변기수 */
	private String MALE_UIL_CNT; /* 남성용-소변기수 */
	private String MALE_DSPSN_WTRCLS_CNT; /* 남성용-장애인용대변기수 */
	private String MALE_DSPSN_UIL_CNT; /* 남성용-장애인용소변기수 */
	private String MALE_KID_WTRCLS_CNT; /* 남성용-어린이용대변기수 */
	private String MALE_KID_UIL_CNT; /* 남성용-어린이용소변기수 */
	private String FEMALE_WTRCLS_CNT; /* 여성용-대변기수 */
	private String FEMALE_DSPSN_WTRCLS_CNT; /* 여성용-장애인용대변기수 */
	private String FEMALE_KID_WTRCLS_CNT; /* 여성용-어린이용대변기수 */
	private String MNGINST_NM; /* 관리기관명 */
	private String MNGINST_TELNO; /* 전화번호 */
	private String OPEN_TM_INFO; /* 개방시간 */
	private String INSTL_YM; /* 설치연월 */
	private String REFINE_WGS84_LAT; /* 위도 */
	private String REFINE_WGS84_LOGT; /* 경도 */
	private String TOILET_POSESN_DIV; /* 화장실소유구분 */
	private String TOILET_INSTL_PLC_TYPE; /* 화장실설치장소유형 */
	private String PROC_CONT; /* 오물처리방식 */
	private String EMBEL_INSTL_YN; /* 비상벨설치여부 */
	private String EMBEL_INSTL_PLC; /* 비상벨설치장소 */
	private String TOILET_YN; /* 화장실여부 */
	private String INSTL_YN; /* 설치여부 */
	private String PLC_NM; /* 기저귀교환대장소 */
	private String RE_BUILD_YM; /* 리모델링연월 */
	private String DATA_STD_DE; /* 데이터기준일자 */
}
