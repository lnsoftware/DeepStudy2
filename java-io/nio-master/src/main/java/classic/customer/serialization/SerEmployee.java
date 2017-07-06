package classic.customer.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by yifan on 17-4-8.
 */
public class SerEmployee implements Serializable {

    private Employee emp;
    private String name;

    SerEmployee(String name){
        this.name = name;
        emp = new Employee(name);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeUTF(name);
    }

    private void readObject(ObjectInputStream ois)
      throws ClassNotFoundException,IOException{
        name = ois.readUTF();
        emp = new Employee(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
