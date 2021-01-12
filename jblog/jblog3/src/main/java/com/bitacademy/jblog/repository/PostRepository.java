package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.PostCategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<PostCategoryVo> searchPost(String keyword) {
		return sqlSession.selectList("post.searchPost", keyword);
	}
	
	public int createNewPost(PostVo postVo) {
		return sqlSession.insert("post.createNewPost", postVo);
	}
	
	public List<PostVo> getPostList(String id) {
		return sqlSession.selectList("post.getPostList", id);
	}

	public int deleteEveryPostInCategory(String catNo) {
		return sqlSession.delete("post.deleteEveryPostInCategory", catNo);
	}

	public PostVo getPostfromPosNo(String posNo) {
		return sqlSession.selectOne("post.getPostfromPosNo", posNo);
	}
}
