package com.hg.spring.cache.rediscache.service;

import com.hg.spring.cache.rediscache.entity.User;
import com.hg.spring.cache.rediscache.service.impl.ck.UserDelCache;
import com.hg.spring.cache.rediscache.service.impl.ck.UserSaveCache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.util.List;

/**
 * Created by hg on 2016/7/5.
 */
public interface IUserInfoService {

    @UserSaveCache
    User findById(Long id);

    void deleteById(Long id);

    @Caching(
            put = {
                    @CachePut(  key = "#root.caches[0].name+'.id.'+#u.id"),
//                    @CachePut(  key = "#user.username"),
//                    @CachePut( key = "#user.email")
            }
    )
    void insert(User u);

    @UserDelCache
    void updateUser(Long id, String newName);

    public List<User> selectAll();
}
