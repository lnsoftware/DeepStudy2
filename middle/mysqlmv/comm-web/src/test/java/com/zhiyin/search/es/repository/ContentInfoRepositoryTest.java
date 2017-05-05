package com.zhiyin.search.es.repository;

import com.zhiyin.search.es.controller.ControllerTestBase;
import com.zhiyin.search.es.module.content.entity.ContentInfoMapping;
import com.zhiyin.search.es.module.content.repository.ContentInfoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/3/28.
 */
public class ContentInfoRepositoryTest extends ControllerTestBase {


    @Resource
    private ContentInfoRepository contentInfoRepository;

    ContentInfoMapping entity = new ContentInfoMapping();

    @Before
    public void set() {
        entity.setId(100L);
        entity.setTitle("hello");
    }

    @Test
    public void testSuit() {
        ContentInfoMapping ret = contentInfoRepository.save(entity);
        Assert.assertTrue(ret.getId() == entity.getId());

        ContentInfoMapping find = contentInfoRepository.findOne(entity.getId());
        Assert.assertTrue(find != null);

        contentInfoRepository.delete(entity.getId());

        find = contentInfoRepository.findOne(entity.getId());
        Assert.assertTrue(find == null);

    }

}