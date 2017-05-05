import com.ge.snowizard.exceptions.InvalidSystemClock;
import org.predictor.idgenerator.BasicEntityIdGenerator;
import org.predictor.idgenerator.GetHardwareIdFailedException;
import org.predictor.idgenerator.InvalidSystemClockException;
import com.ge.snowizard.core.IdWorker;

import static org.junit.Assert.assertTrue;

/**
 * Created by wangqinghui on 2016/4/28.
 */
public class Test {

    public static void main(String[] args) throws GetHardwareIdFailedException, InvalidSystemClockException, InvalidSystemClock {


        final IdWorker worker = new IdWorker(1, 1);


        for(int i = 0; i< 10; i++ ){
            String result = BasicEntityIdGenerator.getInstance().generateLongId();

            printInfo(Long.valueOf(result));

            printInfo(worker.nextId() );

            System.out.println( );

        }

    }


    public static void printInfo(Long l){



        String bin = Long.toBinaryString(l);

        String newStr = bin;
        for(int i=0; i < (64-bin.length()) ; i++){
            newStr = "0" + newStr;
        }

        bin = newStr;


        String date = bin.substring(0,41);
        String mach = bin.substring(42,52);
        String seq = bin.substring(52);
        System.out.println(bin.length() + " " + date.length() + " " + mach.length() + " " + seq.length() );
        System.out.println( l + "  "+ date + " " + mach + " "+seq );
    }
}
