package classic.Stream;

import java.io.Serializable;

/**
 * Created by yifan on 2017/4/5.
 */
public class SerEmployee extends Employee implements Serializable {

    private static final long serialVersionUID = 7717145344652283090L;

    SerEmployee(String name) {
        super(name);
    }
}
