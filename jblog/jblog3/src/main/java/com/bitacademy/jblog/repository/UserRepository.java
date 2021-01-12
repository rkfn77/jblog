package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}

	public UserVo findByIdAndPassword(UserVo userVo) {
		return sqlSession.selectOne("user.findByIdAndPassword", userVo);
	}

	public List<UserVo> searchUser(String keyword) {
		return sqlSession.selectList("user.searchUser", keyword);
	}

	public int idExist(String user_id) {
		return sqlSession.selectOne("user.idExist", user_id);
	}

}
