package com.asgmt.task.toilet.service;

import java.util.List;

import com.asgmt.task.toilet.vo.ToiletDtlPVo;
import com.asgmt.task.toilet.vo.ToiletDtlRVo;
import com.asgmt.task.toilet.vo.ToiletPVo;
import com.asgmt.task.toilet.vo.ToiletRVo;

public interface ToiletService {

	public List<ToiletRVo> selectToiletList(ToiletPVo paramVO);

	public ToiletDtlRVo selectToiletDtl(ToiletDtlPVo paramVO);

	public ToiletDtlRVo selectCloseToiletDtl(ToiletDtlPVo paramVO);
}
