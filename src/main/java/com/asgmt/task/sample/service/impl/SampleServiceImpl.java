package com.asgmt.task.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asgmt.task.sample.dao.SampleDao;
import com.asgmt.task.sample.service.SampleService;
import com.asgmt.task.sample.vo.SamplePVo;
import com.asgmt.task.sample.vo.SampleRVo;

@Service
public class SampleServiceImpl implements SampleService{

	@Autowired
	private SampleDao sample;

	@Override
	public List<SampleRVo> selectTable(SamplePVo paramVo) {
		return sample.selectTable(paramVo);
	};
}
