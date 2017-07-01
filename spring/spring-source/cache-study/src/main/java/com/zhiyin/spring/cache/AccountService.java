package com.zhiyin.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching
@Service
public class AccountService {

    @Cacheable(value = "accountCache")// 使用了一个缓存名叫 accountCache
    public Account getAccountByName(String userName) {
        System.out.println("real query account." + userName);
        return getFromDB(userName);
    }

    private Account getFromDB(String acctName) {
        System.out.println("real querying db..." + acctName);
        return new Account(acctName);
    }

}