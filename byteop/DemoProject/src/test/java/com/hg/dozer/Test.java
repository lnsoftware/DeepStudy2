package com.hg.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.util.MappingUtils;

import com.hg.dozer.vo.TestObjectFull;
import com.hg.dozer.vo.TestObjectPart;

public class Test {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		files.add("dozer.xml");
		Mapper mapper = new DozerBeanMapper(files);
		
		TestObjectFull objFull = new TestObjectFull();
		objFull.setId("1");
		objFull.setName("hello");
		objFull.setAge("20");
		
		TestObjectPart destObject =  
		    mapper.map(objFull, TestObjectPart.class);
		
//		MappingUtil
		System.out.println(destObject.getBianhao() +" "+ destObject.getXingming());
	}

}
