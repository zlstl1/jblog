package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		return sqlSession.insert("user.insertUser",userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return sqlSession.selectOne("user.login",userVo);
	}
	
	public boolean isExists(String id) {
		UserVo userVo = sqlSession.selectOne("user.isExists",id);
		return (userVo!=null);
	}
}

