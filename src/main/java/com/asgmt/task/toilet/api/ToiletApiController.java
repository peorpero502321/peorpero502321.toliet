package com.asgmt.task.toilet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asgmt.task.toilet.service.ToiletService;
import com.asgmt.task.toilet.vo.ToiletPVo;
import com.asgmt.task.toilet.vo.ToiletRVo;

@RestController
public class ToiletApiController {
	
	@Autowired
	private ToiletService toilet;
	
	@RequestMapping(value="/api/toilet", method = RequestMethod.GET)
	public List<ToiletRVo> selectToiletList(ToiletPVo paramVO) {
		
		System.out.println("화장실 페이지 호출 + data" + toilet.selectToiletList(paramVO));
		return toilet.selectToiletList(paramVO);
	}
}
