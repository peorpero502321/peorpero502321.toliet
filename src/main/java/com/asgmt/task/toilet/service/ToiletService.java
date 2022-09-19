package com.asgmt.task.toilet.service;

import java.util.List;

import com.asgmt.task.toilet.vo.ToiletRVo;

public interface ToiletService {

	List<ToiletRVo> selectToiletList();
	
}
