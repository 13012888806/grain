package com.xinlin.test.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.xinlin.app.entity.pojo.FileLoad;
import com.xinlin.app.mapper.FileLoadMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jcdf-springmvc-config.xml","classpath*:jcdf-context-test.xml"})
public class FileuploadDaoTest {

	@Autowired
	private FileLoadMapper fileuploadMapper;
	
	public List<FileLoad> list() {
		return null;
	}

	@Test
	public void get() {
		String keyId = "43dde3e0-6ddf-4a5c-b72e-7a1289b03c17";
		FileLoad f = this.fileuploadMapper.selectByPrimaryKey(keyId);
		Assert.notNull(f);
	}

	
	@Test
	public void insert() {
		FileLoad file = new FileLoad();
		String keyId = UUID.randomUUID().toString();
		System.out.println(keyId);
		//  43dde3e0-6ddf-4a5c-b72e-7a1289b03c17
		file.setFileid(keyId);
		file.setFilename("测试专用文件");
		file.setFilemark("remark");
		file.setBlobfile(new String("hello").getBytes());
		
		fileuploadMapper.insert(file);
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteById() {
		// TODO Auto-generated method stub
		
	}

}
