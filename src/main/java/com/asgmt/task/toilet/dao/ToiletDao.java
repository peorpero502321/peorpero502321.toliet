package com.asgmt.task.toilet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.asgmt.task.toilet.vo.ToiletDtlPVo;
import com.asgmt.task.toilet.vo.ToiletDtlRVo;
import com.asgmt.task.toilet.vo.ToiletPVo;
import com.asgmt.task.toilet.vo.ToiletRVo;

@Mapper
@Repository
public interface ToiletDao {

	// 지도 영역좌표기준 목록조회
	public List<ToiletRVo> selectToiletList(ToiletPVo paramVO);

	// 마커 좌표 기준 데이터 조회
	public ToiletDtlRVo selectToiletDtl(ToiletDtlPVo paramVO);

	// 좌표 값과 가까운 화장실 데이터 조회
	public ToiletDtlRVo selectCloseToiletDtl(ToiletDtlPVo paramVO);
}
