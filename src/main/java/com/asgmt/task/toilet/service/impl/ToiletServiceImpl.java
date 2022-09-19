package com.asgmt.task.toilet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asgmt.task.toilet.dao.ToiletDao;
import com.asgmt.task.toilet.service.ToiletService;
import com.asgmt.task.toilet.vo.ToiletRVo;

@Service
public class ToiletServiceImpl implements ToiletService{

	@Autowired
	private ToiletDao toilet;
	
	@Override
	public List<ToiletRVo> selectToiletList() {
		return toilet.selectToilet();
	}
	
}
