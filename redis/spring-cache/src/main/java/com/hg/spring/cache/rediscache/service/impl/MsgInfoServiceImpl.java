//package com.hg.spring.cache.rediscache.service.impl;
//
//import com.hg.spring.cache.rediscache.entity.User;
//import com.hg.spring.cache.rediscache.repo.UserRepository;
//import com.hg.spring.cache.rediscache.service.UserInfoService;
//import com.hg.spring.cache.rediscache.service.impl.ck.UserDelCache;
//import com.hg.spring.cache.rediscache.service.impl.ck.UserSaveCache;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
///**
// * Created by hg on 2015/5/25.
// */
//@Slf4j
//@Service("redisCacheService")
//@CacheConfig(cacheNames = {"users.userinfo"}) // 默认cache名称
//public class MsgInfoServiceImpl implements UserInfoService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    public User findByIdNoCache(Long id) {
//
//        return findById(id);
//    }
//
//
//    @UserSaveCache
//    public User findById(Long id) {
//        User user = userRepository.findOne(id);
//        return user;
//    }
//
//
//    public void deleteById(Long id) {
//        userRepository.delete(id);
//    }
//
//    public void insert(User u) {
//        if (u != null && u.getId() != null) {
//            userRepository.save(u);
//        }
//    }
//
//
//    @UserDelCache
//    public void updateUser(Long id, String newName) {
//        log.info("update user info.");
//        userRepository.updateName(id, newName);
//    }
//
//    @Cacheable( {CacheNameFactory.UserSelAll} )
//    public List<User> selectAll() {
//        return userRepository.findAll();
//    }
//
//
//    public Page<User> selectByPage() {
//        Pageable page = new PageRequest(1, 2);
//        Page<User> pageList = this.userRepository.findAll(page);
//        return pageList;
//    }
//}
