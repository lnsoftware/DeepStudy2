package com.bfa.sbgl.app.api.computer.service;

/**
 * Created by hg on 2016/2/2.
 */

import com.bfa.sbgl.app.api.computer.entity.Computercategory;

import java.util.List;
import java.util.Map;




public interface ComputercategoryService {
    public Computercategory selectById(int id);
    public Computercategory selectByTypeLan(Map<String, Object> map);
    public List<Computercategory> selectAllByLan(int languagetype);
    Computercategory selectByTypeLan(Integer computercategorytype,
                                     Integer languagetype);
}
