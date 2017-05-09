package com.mo.test.service;

import com.mo.test.mapper.MyBatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MoXingwang on 2017/6/13.
 */
@Service
public class TransactionalServiceB {

    @Autowired
    private MyBatisTest myBatisTest;

    @Transactional
    public void onTransactional(){
         int result = myBatisTest.insert("onTransactional");


         if(1 == result){
             throw new RuntimeException("test");
         }
        System.out.println();

    }

    public void noTransactional(){
        int result = myBatisTest.insert("onTransactional");


        if(1 == result){
            throw new RuntimeException("test");
        }

        System.out.println();
    }


    @Transactional
    public void on2noTransactional(){
        noTransactional();
        System.out.println();

    }

    public void no2onTransactional(){
        this.onTransactional();
        System.out.println();

    }

    public void no2noTransactional(){
        noTransactional();
        System.out.println();

    }
}
