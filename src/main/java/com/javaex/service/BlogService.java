package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	public Map<String,Object> getBlogInfo(String id,int cateNo,int postNo) {
		Map<String,Object> blogInfoMap = new HashMap<String,Object>();
		BlogVo blogVo = blogDao.getBlog(id);
		blogInfoMap.put("blogVo", blogVo);
		List<CategoryVo> categoryList = categoryDao.getCategory(id);
		blogInfoMap.put("categoryList", categoryList);
		List<PostVo> postList=null;
		PostVo mainPost=null;
		
		if(!categoryList.isEmpty()) {
			if(cateNo==-1) {
				postList = postDao.getPost(categoryList.get(0).getCateNo());
			}else {
				postList = postDao.getPost(cateNo);
			}
		}
		blogInfoMap.put("postList", postList);
		
		if(!postList.isEmpty()) {
			if(postNo==-1) {
				mainPost = postDao.getMainPost(postList.get(0).getPostNo());
			}else {
				mainPost = postDao.getMainPost(postNo);
			}
		}
		
		blogInfoMap.put("mainPost", mainPost);
		
		
		return blogInfoMap;
	}
	
	public BlogVo getBlog(String id) {
		return blogDao.getBlog(id);
	}
	
	public void setBlog(String id,String blogTitle,MultipartFile file) {
		Map<String,Object> blogSetInfoMap = new HashMap<String,Object>();
		blogSetInfoMap.put("id", id);
		blogSetInfoMap.put("blogTitle", blogTitle);
		
		String exName = "";
		String saveName = "";
		
		try {
			if(!file.isEmpty()){
				exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
				
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream( "D:\\javaStudy\\logo\\" + saveName );
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
				
				BlogVo blogVo = blogDao.getBlog(id);
				
				File deleteFile = new File("D:\\javaStudy\\logo\\" + blogVo.getLogoFile());
				if( deleteFile.exists() ){
					deleteFile.delete();
		        }
				
				if(bout != null) {
					bout.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		blogSetInfoMap.put("logoFile", saveName);
		
		blogDao.setBlog(blogSetInfoMap);
	}
}
