
//package application.services;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.jdbc.core.JdbcTemplate;

public class IsolationLevelAdvice implements MethodInterceptor {

	private Map methodJdbcTemplateMap;

	private JdbcTemplate defaultJdbcTemplate;

	public IsolationLevelAdvice(Map methodJdbcTemplateMap,
			JdbcTemplate defaultJdbcTemplate) {
		super();
		this.defaultJdbcTemplate = defaultJdbcTemplate;
		this.methodJdbcTemplateMap = methodJdbcTemplateMap;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		boolean set = false;
		try {
			Method method = invocation.getMethod();
			set = setThreadJdbcTemplate(method);
			Object rval = invocation.proceed();
			return rval;
		} finally {
			if (set) {
				unsetThreadJdbcTemplate();
			}
		}
	}

	public boolean setThreadJdbcTemplate(Method method) {

		boolean set = false;
		if (IsolationLevelUtil.getJdbcTemplate() == null) {
			JdbcTemplate jdbcTemplate = null;
			String methodName = method.getName();
			Iterator methodPatterns = methodJdbcTemplateMap.keySet().iterator();
			while (methodPatterns.hasNext()) {
				String methodPattern = (String) methodPatterns.next();
				if (Pattern.matches(methodPattern, methodName)) {
					jdbcTemplate = (JdbcTemplate) methodJdbcTemplateMap
							.get(methodPattern);
					break;
				}
			}
			if (jdbcTemplate == null) {
				jdbcTemplate = defaultJdbcTemplate;
			}

			IsolationLevelUtil.setJdbcTemplate(jdbcTemplate);
			set = true;
		}
		return set;
	}

	public void unsetThreadJdbcTemplate() {
		IsolationLevelUtil.setJdbcTemplate(null);
	}

}
