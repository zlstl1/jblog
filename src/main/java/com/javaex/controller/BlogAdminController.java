package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping(value="/{id}/admin")
public class BlogAdminController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/basic", method=RequestMethod.GET )
	public String basic(@PathVariable("id") String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping(value="/basicupdate", method=RequestMethod.POST )
	public String basicUpdate(@PathVariable("id") String id,@RequestParam("blogTitle") String blogTitle,@RequestParam("file") MultipartFile file,Model model) {
		blogService.setBlog(id,blogTitle,file);
		
		return "redirect:/{id}/admin/basic";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET )
	public String category(@PathVariable("id") String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping(value="/getcategory", method=RequestMethod.POST )
	public List<CategoryVo> getCategory(@PathVariable("id") String id) {
		List<CategoryVo> categoryList = categoryService.getCategory(id);
		
		return categoryList;
	}
	
	@ResponseBody
	@RequestMapping(value="/addcategory", method=RequestMethod.POST )
	public CategoryVo addCategory(@PathVariable("id") String id,@RequestBody CategoryVo categoryVo) {
		categoryVo.setId(id);
		categoryVo = categoryService.addCategory(categoryVo);
		return categoryVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/deletecategory", method=RequestMethod.POST )
	public int deleteCategory(@RequestParam("cateNo") int cateNo) {
		return categoryService.deleteCategory(cateNo);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET )
	public String write(@PathVariable("id") String id,Model model) {
		List<CategoryVo> categoryList = categoryService.getCategory(id);
		model.addAttribute("categoryList", categoryList);
		
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/writepost", method=RequestMethod.POST )
	public String writepost(@PathVariable("id") String id,Model model,@ModelAttribute PostVo postVo) {
		postService.writePost(postVo);
		
		return "redirect:/{id}/admin/write";
	}
	
}