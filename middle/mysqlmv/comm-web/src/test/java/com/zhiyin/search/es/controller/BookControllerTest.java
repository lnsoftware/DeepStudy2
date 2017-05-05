package com.zhiyin.search.es.controller;

import com.zhiyin.search.es.module.book.entity.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends ControllerTestBase {

    private Log log = LogFactory.getLog(BookControllerTest.class);

    /**
     * 测试性接口
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {

        String url = "/books/test";
        log.info("start test " + url);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(result);

    }

    @Test
    public void getBook() throws Exception {

        String url = "/books/get/10";
        log.info("start test " + url);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(result);

    }

    /**
     * 添加
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {

        String url = "/books/add";
        log.info("start test " + url);

        Book book = new Book();
        long id = (long) (Math.random() * 10000);
//		book.setId((long) (Math.random()*10000));
        book.setId(id);
        book.setName("hello");

        this.sendPost(url, book);


        url = "/books/get/" + id;
        log.info("start test " + url);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(result);
    }

    @Test
    public void deleteAll() throws Exception {

        String url = "/books/deleteAll";
        log.info("start test " + url);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(result);

    }
}
