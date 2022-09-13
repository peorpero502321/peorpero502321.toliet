package com.asgmt.task.toilet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ToiletDao {
	
	public List<String> selectToilet();
}
