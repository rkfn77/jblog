package com.bitacademy.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.repository.CategoryRepository;
import com.bitacademy.jblog.repository.PostRepository;
import com.bitacademy.jblog.repository.UserRepository;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Service
public class BlogService {
	
	private static final String SAVE_PATH = "/jblog-uploads";
	private static final String URL_BASE = "/assets/images/";

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public int writePost(PostVo postVo) {
		return postRepository.createNewPost(postVo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.getCategoryList(id);
	}

	public Map<String, Object> getCategoryListAndCountPages(String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<CategoryVo> list = categoryRepository.getCategoryList(id);
		map.put("list", list);
		
		List<Long> countPost = new ArrayList<Long>();
		// 추후 마이바티스 for each 문으로 IO 한번에 하여 성능 향상
		for (CategoryVo categoryVo : list) {
			countPost.add(categoryRepository.getCountPost(categoryVo));
		}
		
		map.put("countPost", countPost);
		
		return map;
	}

	public int writeCategory(CategoryVo categoryVo) {
		return categoryRepository.writeCategory(categoryVo);
		
	}

	public List<PostVo> getPostList(String id) {
		return postRepository.getPostList(id);
	}

	public Map<String, Object> getCategoryListAndGetPostList(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CategoryVo> catList = categoryRepository.getCategoryList(id);
		List<PostVo> postList = postRepository.getPostList(id);

		map.put("catList", catList);
		map.put("postList", postList);
		
		return map;
	}

	public BlogVo getLogoPathAndTitle(String id) {
		return blogRepository.getLogoPathAndTitle(id);
	}

	public int updateBlogInfo(BlogVo blogVo) {
		return blogRepository.updateBlogInfo(blogVo);
		
	}

	public String restore(MultipartFile multipartFile) {
		
		String url = "";
		
		try {
		
		String originFilename = multipartFile.getOriginalFilename();
		String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
		String saveFilename = genSaveFilename(extName);
		
		Long fileSize = multipartFile.getSize();
		
		System.out.print("########" + originFilename);
		System.out.print("########" + saveFilename);
		System.out.print("########" + fileSize);
		
		
		
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		os.write(fileData);
		os.close();
		
		url = URL_BASE + "/" + saveFilename;
		
		} catch (IOException e) {
			throw new RuntimeException("file upload error : " + e);
		}
		return url;
	}
	
	private String genSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		
		filename += ("." + extName);
		
		return filename;
	}

	// transaction 처리
	public void deleteCategory(String catNo) {
		int k = 0;
		k = postRepository.deleteEveryPostInCategory(catNo);
		k = categoryRepository.deleteCategorybyCatNo(catNo);
		return;
		
	}

	public CategoryVo getCategoryfromCatNo(String CatNo) {
		return categoryRepository.getCategoryfromCatNo(CatNo);
	}

	public PostVo getPostfromPosNo(String PosNo) {
		return postRepository.getPostfromPosNo(PosNo);
	}

	public int idExist(String user_id) {
		return userRepository.idExist(user_id);
	}

}
