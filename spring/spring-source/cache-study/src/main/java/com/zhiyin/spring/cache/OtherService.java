package com.zhiyin.spring.cache;

import org.springframework.stereotype.Service;

@Service
public class OtherService {

    public Account getAccountByName(String userName) {
        System.out.println("real query account." + userName);
        return getFromDB(userName);
    }

    private Account getFromDB(String acctName) {
        System.out.println("real querying db..." + acctName);
        return new Account(acctName);
    }
}