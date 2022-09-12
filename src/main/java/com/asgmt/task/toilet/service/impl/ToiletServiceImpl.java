package com.asgmt.task.toilet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asgmt.task.toilet.dao.ToiletDao;
import com.asgmt.task.toilet.service.ToiletService;

@Service
public class ToiletServiceImpl implements ToiletService{

	@Autowired
	private ToiletDao toilet;
	
	@Override
	public List<String> selectToiletList() {
		List<String> toiletList = new ArrayList<String>();
		
		toiletList.add(0, "test0");
		toiletList.add(1, "test1");
		toiletList.add(2, "test2");
		return toiletList;
	}
	
}
