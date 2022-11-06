package com.asgmt.task.toilet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asgmt.task.toilet.dao.ToiletDao;
import com.asgmt.task.toilet.service.ToiletService;
import com.asgmt.task.toilet.vo.ToiletDtlPVo;
import com.asgmt.task.toilet.vo.ToiletDtlRVo;
import com.asgmt.task.toilet.vo.ToiletPVo;
import com.asgmt.task.toilet.vo.ToiletRVo;

@Service
public class ToiletServiceImpl implements ToiletService{

	@Autowired
	private ToiletDao toilet;

	@Override
	public List<ToiletRVo> selectToiletList(ToiletPVo paramVO) {
		return toilet.selectToiletList(paramVO);
	}

	@Override
	public ToiletDtlRVo selectToiletDtl(ToiletDtlPVo paramVO) {
		String strLat = paramVO.getLat();
		String strLogt = paramVO.getLogt();
		String[] lstLat = strLat.split("\\.");
		String[] lstLogt = strLogt.split("\\.");
		paramVO.setLat(lstLat[0] + "." +lstLat[1].substring(0, 5));
		paramVO.setLogt(lstLogt[0] + "." + lstLogt[1].substring(0, 5));
		return toilet.selectToiletDtl(paramVO);
	}

	@Override
	public ToiletDtlRVo selectCloseToiletDtl(ToiletDtlPVo paramVO) {
		return toilet.selectCloseToiletDtl(paramVO);
	}

}
