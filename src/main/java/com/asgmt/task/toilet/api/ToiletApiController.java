package com.asgmt.task.toilet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asgmt.task.toilet.service.ToiletService;

@RestController
public class ToiletApiController {
	
	@Autowired
	private ToiletService toilet;
	
	@RequestMapping(value="/api/toilet", method = RequestMethod.GET)
	public List<String> selectToiletList() {
		
		System.out.println("화장실 페이지 호출 + data" + toilet.selectToiletList());
		return toilet.selectToiletList();
	}
}
