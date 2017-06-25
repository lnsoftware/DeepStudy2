package synchronize;

/**
 * Created by hg on 2017/6/25.
 */
public class Basic {

    private Integer count = 0 ;

    public void syncCount(){

        synchronized(this){
            count++;
        }
    }

    public static void main(String[] args) {

    }

}
