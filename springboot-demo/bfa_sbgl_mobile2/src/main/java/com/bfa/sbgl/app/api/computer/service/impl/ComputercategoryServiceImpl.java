package com.bfa.sbgl.app.api.computer.service.impl;



import com.bfa.sbgl.app.api.computer.entity.Computercategory;
import com.bfa.sbgl.app.api.computer.mapper.ComputercategoryMapper;
import com.bfa.sbgl.app.api.computer.service.ComputercategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("computercategoryService")
public class ComputercategoryServiceImpl implements ComputercategoryService {

	private final static Log log = LogFactory.getLog(ComputercategoryServiceImpl.class);
	
	@Resource
	private ComputercategoryMapper computercategoryMapper;

	
	@Override
	public Computercategory selectById(int id) {
		// TODO Auto-generated method stub
		return computercategoryMapper.selectById(id);
	}

	@Override
	public List<Computercategory> selectAllByLan(int languagetype) {
		// TODO Auto-generated method stub
		return computercategoryMapper.selectAllByLan(languagetype);
	}

	@Override
	public Computercategory selectByTypeLan(Map<String, Object> map) {
		return computercategoryMapper.selectByTypeLan(map);
	}
	
	@Override
	public Computercategory selectByTypeLan(Integer computercategorytype, Integer languagetype){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("computercategorytype", computercategorytype);
		map.put("languagetype", languagetype);
		return computercategoryMapper.selectByTypeLan(map);
	}
	
	
}
