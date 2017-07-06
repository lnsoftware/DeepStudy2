package classic;

import java.io.Serializable;

/**
 * Created by yifan on 3/31/17.
 */
public class Employee implements Serializable{
    private String name;
    private int age;

    public Employee(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {return this.name;}
    public int getAGe(){return this.age;}
}
