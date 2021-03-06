package com.github.plugin.monitor;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: 吴海旭
 * Date: 2017-06-29
 * Time: 下午1:36
 */
public class MonitorInterceptorTest {

	private Logger logger = LoggerFactory.getLogger(MonitorInterceptorTest.class);
	private SqlSession sqlSession = null;

	@Before
	public void setUp() {
		sqlSession = MonitorConfigHelper.getSqlSession();
		deleteAll();
	}

	@After
	public void destroy() {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

	@Test
	public void deleteAll() {

		sqlSession.selectOne("MailMapper.selectMailById",1);
	}
}
