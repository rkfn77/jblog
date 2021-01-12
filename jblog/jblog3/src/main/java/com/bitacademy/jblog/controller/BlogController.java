package com.bitacademy.jblog.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping({ "", "/{category}", "/{category}/{post}" })
	public String index(
			@PathVariable String id, 
			@PathVariable Optional<Long> category,
			@PathVariable Optional<Long> post, 
			Model model) {

		System.out.println(id +" " + category+" " + post);				
		BlogVo blogVo = blogService.getLogoPathAndTitle(id);
		
		List<CategoryVo> categoryList =  blogService.getCategoryList(id);	
		List<PostVo> postList = blogService.getPostList(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		
		
		return "blog/blog-main";
	}
	
}
