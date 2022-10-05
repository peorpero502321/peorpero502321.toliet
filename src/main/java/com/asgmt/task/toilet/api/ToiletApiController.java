package com.asgmt.task.toilet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asgmt.task.toilet.service.ToiletService;
import com.asgmt.task.toilet.vo.ToiletDtlPVo;
import com.asgmt.task.toilet.vo.ToiletDtlRVo;
import com.asgmt.task.toilet.vo.ToiletPVo;
import com.asgmt.task.toilet.vo.ToiletRVo;

@RestController
public class ToiletApiController {

	@Autowired
	private ToiletService toilet;

	// 지도 범위에따라서 목록조회
	@RequestMapping(value="/api/toilet", method = RequestMethod.GET)
	public List<ToiletRVo> selectToiletList(ToiletPVo paramVO) {
		return toilet.selectToiletList(paramVO);
	}

	@RequestMapping(value="/api/toilet/dtl", method = RequestMethod.GET)
	public ToiletDtlRVo selectToiletDtl(ToiletDtlPVo paramVO) {
		return toilet.selectToiletDtl(paramVO);
	}
}
