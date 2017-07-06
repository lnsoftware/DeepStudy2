package com.zhiyin.mysql.acid;

import com.zhiyin.mysql.lock.domain.User;
import com.zhiyin.mysql.lock.domain.UserMapper;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("userServiceCustomTrans")
public class UserServiceCustomTrans implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlatformTransactionManager txManager;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insert(User user){
        userMapper.insert(user);
//        userMapper.insert(user.getName(),user.getAge());
    }

    @Override
    public List<User> selectAll( ) {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectAll(int isoLevel) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setIsolationLevel(isoLevel);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            return userMapper.selectAll();
        }finally {
            txManager.commit(status);
        }

    }

    @Override
    public void insertLong(User user){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            userMapper.insert(user);
           sleep(10);
        }
        catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);
    }

    @Override
    public void updateLong(User user){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            userMapper.insert(user);
        }
        catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);
    }

    private void sleep(int t){
        try {
            TimeUnit.SECONDS.sleep(t);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}