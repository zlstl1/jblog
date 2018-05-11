package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlog",id);
	}
	
	public void setBlog(Map<String, Object> blogSetInfoMap) {
		sqlSession.update("blog.setBlog",blogSetInfoMap);
	}
	
	public int insertBlog(UserVo userVo) {
		return sqlSession.insert("blog.insertBlog",userVo);
	}
}
