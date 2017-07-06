package classic.externalization;


import classic.customer.serialization.SerEmployee;

import java.io.*;

/**
 * Created by yifan on 17-4-8.
 */
public class ExternalizationDemo {

    private static String FILE_NAME = "/home/yifan/learn/java/nio_note/exercise/employee.txt";
    public static void main(String[] args){

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try{
         oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
         Employee se = new Employee("John Doe",15);
         oos.writeObject(se);
         oos.close();
         oos = null;

         System.out.println("se object written to file");
         ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
         se = (Employee)ois.readObject();
         System.out.println("se object read from file");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            if(oos != null){
                try{
                 oos.close();
                }catch(IOException ioe){
                    assert false;
                }
            }

            if(ois != null){
                try {
                    ois.close();
                }catch (IOException ioe){
                    assert false;
                }
            }
        }
    }
}
