import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangqinghui on 2018/2/5.
 */
public class T
{

    public static void main(String[] args) throws IOException {

        String str = "";
        for (int i = 0; i < 2048; i++) {
            String tmp = ("alter table order_brief_u_" + i + " modify sku_name varchar(200); ");
            str += tmp;
        }
        FileUtils.writeStringToFile(new File("sss"),str);
    }
}
