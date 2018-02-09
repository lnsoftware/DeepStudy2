package demo;

import lombok.Data;

import java.rmi.Naming;

/**
 * Created by wangqinghui on 2017/12/21.
 */
@Data
public class Student extends BaseUser{
    private String name;

    public static void main(String[] args) {
        BaseUser user = new Student();
        user.setId(1L);
//        user.set
    }
}
