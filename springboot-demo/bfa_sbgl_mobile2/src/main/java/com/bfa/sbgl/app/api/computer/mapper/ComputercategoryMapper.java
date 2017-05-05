package com.bfa.sbgl.app.api.computer.mapper;



import com.bfa.sbgl.app.api.computer.entity.Computercategory;

import java.util.List;
import java.util.Map;

public interface ComputercategoryMapper {

	public Computercategory selectById(int id);
	
	public Computercategory selectByTypeLan(Map<String, Object> map);
	
	public List<Computercategory> selectAllByLan(int languagetype);
//	public List<Computercategory> selectAll();
}
