
package com.hg.spring.trans.mapper;

import com.hg.spring.trans.entity.Village;
import org.apache.ibatis.annotations.Insert;
public interface VillageMapper {
  @Insert("INSERT into village(id,name,district) VALUES(#{vid}, #{villageName}, #{district})")
  void insertVillage(Village village);
} 