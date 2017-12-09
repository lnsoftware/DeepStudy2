package com.zhiyin.spring.mvc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhiyin.spring.mvc.model.User;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserInfoController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void listUsers() throws Exception {
        this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()) ;
    }

    @Test
    public void post2() throws Exception {
        User u = new User();
        u.setId(1L);
        this.mvc.perform(post("/jsonPost").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(u)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;
    }

    @Test
    public void postList() throws Exception {
        User u = new User();
        u.setId(1L);
        List<User> list = Lists.newArrayList(u);
        String reqStr = JSON.toJSONString(list);
        this.mvc.perform(post("/postList").contentType(MediaType.APPLICATION_JSON).content( reqStr ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;
    }

    @Test
    public void postMap() throws Exception {
        User u = new User();
        u.setId(1L);
        Map<String,User> map = Maps.newHashMap();
        map.put("1",u);
        this.mvc.perform(post("/postMap").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(map)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;
    }


    @Test
    public void postComplex() throws Exception {
        User u = new User();
        u.setId(1L);
        List<User> list = Lists.newArrayList(u);
        Map<String,List<User>> map = Maps.newHashMap();
        map.put("1",list);
        String reqStr = JSON.toJSONString(map);
        this.mvc.perform(post("/postComplex").contentType(MediaType.APPLICATION_JSON).content( reqStr ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;
    }


}
