package classic;

import java.io.*;

/**
 * Created by yifan on 3/31/17.
 */
public class SerializatioinDemo {
    final static String FILENAME = "/home/yifan/work/tmp/employee_serial.dat";

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);

            Employee emp = new Employee("John Doe",36);
            oos.writeObject(emp);
            oos.close();

            oos = null;

            FileInputStream fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);

            // cast
            emp = (Employee) ois.readObject();
            ois.close();

            System.out.println(emp.getName());
            System.out.println(emp.getAGe());

        } catch (ClassNotFoundException cnfe){
            System.err.println(cnfe.getMessage());
        } catch (IOException ioe){
            System.err.println(ioe.getMessage());
        } finally {
            if( oos != null)
                try{
                    oos.close();
                }catch(IOException ioe){
                    assert false;
                }

            if( ois != null)
                try{
                    ois.close();
                }catch(IOException e){
                    assert false;
                }
        }

    }
}
