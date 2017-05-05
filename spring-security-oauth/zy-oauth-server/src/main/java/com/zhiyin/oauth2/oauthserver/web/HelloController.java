package com.zhiyin.oauth2.oauthserver.web;



import com.google.common.base.Optional;
import com.zhiyin.oauth2.oauthserver.user.*;
import com.zhiyin.oauth2.oauthserver.user.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * just for test server.
 * Created by hg on 2016/6/15.
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @RequestMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name", required = false) String name) {
        name = Optional.fromNullable(name).or("default");
        return ResponseEntity.ok("hello "+name + ", I'm oauth-server.");
    }

    @RequestMapping("/inisys")
    public ResponseEntity<String> hello() {

        UserInfo findVal = userInfoRepo.findByTelephone("admin");
        if(findVal == null){
            UserInfo tmp = new UserInfo();
            tmp.setId(0L);
            tmp.setTelephone("admin");
            tmp.setPassword(DigestUtils.md5Hex("admin"));
            userInfoRepo.save(tmp);
        }
        return ResponseEntity.ok("ok");
    }


}
