package com.hg.dozer;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import com.hg.dozer.vo.A;
import com.hg.dozer.vo.B;
import com.hg.dozer.vo.C;

public class Test2 {
	public static void main(String[] args) {
		 A a = new A();
		    a.setA(1);
		    a.setB(2);

		    B b = new B();
		    b.setC("3");

		    Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		    C destination = mapper.map(a, C.class);

		    mapper.map(b, destination);

		    System.out.println(destination.getA());
	}
}




