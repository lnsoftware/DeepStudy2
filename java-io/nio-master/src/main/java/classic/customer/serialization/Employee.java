package classic.customer.serialization;

/**
 * Created by yifan on 17-4-8.
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
