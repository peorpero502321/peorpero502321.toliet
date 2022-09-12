package com.asgmt.task.toilet.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ToiletController {

	@RequestMapping(value="/app/toilet", method = RequestMethod.GET)
	public String index() {
		
		System.out.println("화장실 페이지");
		return "toilet";
	}
}
