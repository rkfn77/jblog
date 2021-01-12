package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("category.insertCategory", categoryVo);
	}


	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public Long getCountPost(CategoryVo categoryVo) {
		return sqlSession.selectOne("post.getCountPost", categoryVo);
	}
	
	public List<CategoryVo> searchCategory(String keyword) {
		return sqlSession.selectList("category.searchCategory", keyword);
	}
	
	public int writeCategory(CategoryVo categoryVo) {
		return sqlSession.insert("category.insertCategory", categoryVo);
	}


	public int deleteCategorybyCatNo(String catNo) {
		return sqlSession.delete("category.deleteCategorybyCatNo", catNo);
	}

	public CategoryVo getCategoryfromCatNo(String catNo) {
		return sqlSession.selectOne("category.getCategoryfromCatNo", catNo);
	}
}
