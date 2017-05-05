package com.zhiyin.rpc.shi.demo.action;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hg on 2016/7/29.
 */
@Slf4j
@RestController
public class CatDemoController {

    @RequestMapping(value = "/discat", method = RequestMethod.GET)
    public String printWelcome( ) {

        Transaction tr = Cat.getProducer().newTransaction("remote_call", "discatlog");

        Cat.logEvent("remote_server", "remote", Event.SUCCESS, "ip=remote");

        tr.setStatus(Transaction.SUCCESS);
        tr.complete();

        log.info("succ");
        return "ss";
    }

}