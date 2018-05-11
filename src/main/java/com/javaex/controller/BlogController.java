package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET )
	public String blog(@PathVariable("id") String id,Model model,
			@RequestParam( value="cateNo", required=false, defaultValue="-1") Integer cateNo,
			@RequestParam( value="postNo", required=false, defaultValue="-1") Integer postNo
			) {
		Map<String,Object> blogInfoMap = blogService.getBlogInfo(id,cateNo,postNo);
		
		model.addAttribute("id", id);
		model.addAttribute("blogVo", blogInfoMap.get("blogVo"));
		model.addAttribute("categoryList", blogInfoMap.get("categoryList"));
		model.addAttribute("postList", blogInfoMap.get("postList"));
		model.addAttribute("mainPost", blogInfoMap.get("mainPost"));
	
		if(blogInfoMap.get("blogVo") == null) {
			return "main/index";
		} else {
			return "blog/blog-main";
		}
	}
	
}