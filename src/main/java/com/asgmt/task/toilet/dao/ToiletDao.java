package com.asgmt.task.toilet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.asgmt.task.toilet.vo.ToiletRVo;

@Mapper
@Repository
public interface ToiletDao {
	public List<ToiletRVo> selectToilet();
}
