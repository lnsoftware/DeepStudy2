import com.alibaba.fastjson.JSON;
import com.hg.sb.act.entity.TestC2s;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/5/19.
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        for (int i = 1; i < 20; i++) {
            TestC2s c2s = new TestC2s();
            c2s.setId("" + i);
            c2s.setName("hello");
            String str = JSON.toJSONString(c2s);
            System.out.println(str);
        }
        TestC2s c2s = new TestC2s();
        c2s.setId("1");
        c2s.setName("hello");
        String str = JSON.toJSONString(c2s);

        log.info(str);
    }
}
