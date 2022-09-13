package com.asgmt.task.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.asgmt.task.sample.vo.SamplePVo;
import com.asgmt.task.sample.vo.SampleRVo;

@Mapper
@Repository
public interface SampleDao {

	public List<SampleRVo> selectTable(SamplePVo paramVo);

}
