package com.hg.spring.cache.rediscache.service.impl;

import com.alibaba.fastjson.JSON;
import com.hg.spring.cache.rediscache.config.RedisCacheName;
import com.hg.spring.cache.rediscache.entity.User;
import com.hg.spring.cache.rediscache.repo.UserRepository;
import com.hg.spring.cache.rediscache.service.IUserInfoService;
import com.hg.spring.cache.rediscache.service.impl.ck.UserDelCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hg on 2015/5/25.
 */
@Slf4j
@Service("redisCacheService")
@CacheConfig(cacheNames = {RedisCacheName.UserCacheName}) // 默认cache名称
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Cacheable(key = "#root.caches[0].name+'.id.'+#id")
    public User findById(Long id) {
        User user = userRepository.findOne(id);
        log.info(JSON.toJSONString(user));
        return user;
    }

    @CacheEvict(key = "#root.caches[0].name+'.id.'+#id")
    @Override
    public void deleteById(Long id) {
//        userRepository.delete(id);
    }

// 添加尽量不要用缓存，因为插入的数据可能有部分字段没有值，在数据库设置默认值。
//    @Caching(
//            put = {
//                    @CachePut(key = "#root.caches[0].name+'.id.'+#u.id"),
//                    @CachePut(key = "#root.caches[0].name+'.name.'+#u.name")
//            }
//    )
    @Override
    public void insert(User u) {
        if (u != null && u.getId() != null) {
            userRepository.save(u);
        }
    }


    @UserDelCache
    @Override
    public void updateUser(Long id, String newName) {
        log.info("update user info.");
        userRepository.updateName(id, newName);
    }

    @Cacheable({CacheNameFactory.UserSelAll})
    public List<User> selectAll() {
        return userRepository.findAll();
    }


    public Page<User> selectByPage() {
        Pageable page = new PageRequest(1, 2);
        Page<User> pageList = this.userRepository.findAll(page);
        return pageList;
    }
}
