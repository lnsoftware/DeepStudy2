package classic.Stream;

/**
 * Created by yifan on 2017/4/5.
 */
public class Employee {
    private String name;

    Employee(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
