import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Created by hg on 2016/6/23.
 */
public class PasswdTest2 {


    public static void main(String[] args){

//        k123
//        21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5
        String pass = "test";
        String enPass = DigestUtils.sha1Hex(pass);
        System.out.println(enPass);


        enPass = DigestUtils.md5Hex(pass);
        System.out.println(enPass);


    }
}
