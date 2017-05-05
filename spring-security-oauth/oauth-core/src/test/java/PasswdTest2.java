import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Created by hg on 2016/6/23.
 */
public class PasswdTest2 {


    public static void main(String[] args){

//        k123
//        21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5
        String pass = DigestUtils.sha1Hex("app123");
        System.out.println(pass);



    }
}
