package com.bitacademy.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.jblog.vo.UserVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(BlogVo blogVo) {
		return sqlSession.insert("blog.insertBlog", blogVo);
	}

	public BlogVo getLogoPathAndTitle(String id) {
		return sqlSession.selectOne("blog.getLogoPathAndTitle", id);
	}

	public int updateBlogInfo(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlogInfo", blogVo);
	}

	public List<BlogVo> searchBlog(String keyword) {
		return sqlSession.selectList("blog.searchBlog", keyword);
	}


}
