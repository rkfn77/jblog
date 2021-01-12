package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.repository.CategoryRepository;
import com.bitacademy.jblog.repository.UserRepository;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	public boolean join(UserVo userVo) {
		
		boolean ResultChecker;
		CategoryVo categoryVo = new CategoryVo();
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getName() + "블로그");
		blogVo.setLogo("/assets/images/spring-logo.jpg");
		categoryVo.setId(userVo.getId());
		categoryVo.setName("기본설정");
		categoryVo.setDesc("기본설정");
		


		ResultChecker =(userRepository.insertUser(userVo) == 1)
		&&(blogRepository.insertBlog(blogVo) == 1)
		&&(categoryRepository.insertCategory(categoryVo) == 1)
		
		;
		
		return ResultChecker;
	}

	public UserVo getUser(UserVo userVo) {
		return userRepository.findByIdAndPassword(userVo);
	}
}
