package com.zhiyin.mybatis.boot;

import com.alibaba.fastjson.JSON;
import com.zhiyin.mybatis.boot.entity.OrderTask;
import com.zhiyin.mybatis.boot.entity.User;
import com.zhiyin.mybatis.boot.mapper.OrderTaskMapper;
import com.zhiyin.mybatis.boot.mapper.UserMapper;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTaskMapperTest {

	@Autowired
	OrderTaskMapper orderTaskMapper;

	@Test
	public void mapperIsNotNullTest() {
		assertThat(orderTaskMapper).isNotNull();
	}


	@Test
	public void findByStatesTest() {

		for (int i = 0; i < 10; i++) {
			OrderTask orderTask = new OrderTask();
			orderTask.setId(Long.valueOf(i));
			orderTask.setStatus(i%2);
			orderTask.setCreated(DateTime.now().minusHours(i).toDate());
			orderTaskMapper.add(orderTask);
		}

        List<OrderTask> all = orderTaskMapper.selectAll();

        for (int i = 0; i < all.size(); i++) {
            System.out.println(JSON.toJSONString(all.get(i)));
        }

        List<Long> notInList = Lists.newArrayList();
		notInList.add(1L);
        List<Long> ret = orderTaskMapper.selectNotIn(1, notInList,DateTime.now().minusHours(5).toDate());
        System.out.println(ret);
        System.out.println(orderTaskMapper.selectNotIn2(1,notInList,DateTime.now().toDate()));
        System.out.println(orderTaskMapper.selectNotIn3(1,notInList));
    }
}
