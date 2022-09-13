package com.asgmt.task.sample.service;

import java.util.List;

import com.asgmt.task.sample.vo.SamplePVo;
import com.asgmt.task.sample.vo.SampleRVo;

public interface SampleService {

	public List<SampleRVo> selectTable(SamplePVo paramVo);

}
