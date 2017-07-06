package classic.customer.serialization;

import java.io.*;

/**
 * Created by yifan on 17-4-8.
 */
public class SerializationDemo {

    private static String FILE_NAME = "/home/yifan/learn/java/nio_note/exercise/employee.txt";
    public static void main(String[] args){

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try{
         oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
         SerEmployee se = new SerEmployee("John Doe");
         System.out.println(se);
         oos.writeObject(se);
         oos.close();
         oos = null;

         System.out.println("se object written to file");
         ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
         se = (SerEmployee)ois.readObject();
         System.out.println("se object read from file");
         System.out.println(se);
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
