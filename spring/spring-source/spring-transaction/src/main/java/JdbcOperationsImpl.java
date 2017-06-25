//
////package application.storage;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BatchPreparedStatementSetter;
//import org.springframework.jdbc.core.CallableStatementCallback;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.ConnectionCallback;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCallback;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowCallbackHandler;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.StatementCallback;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//
//public class JdbcOperationsImpl implements ApplicationContextAware,
//		JdbcOperations {
//
//	private static final Logger logger = Logger
//			.getLogger(JdbcOperationsImpl.class);
//
//	private ApplicationContext applicationContext;
//
//	private JdbcTemplate defaultJdbcTemplate;
//
//	public JdbcOperationsImpl(JdbcTemplate defaultJdbcTemplate) {
//		super();
//		this.defaultJdbcTemplate = defaultJdbcTemplate;
//	}
//
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext = applicationContext;
//	}
//
//	private JdbcTemplate getJdbcTemplate() {
//		try {
//			return (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
//		} catch (ClassCastException e) {
//			logger
//					.warn(
//							"Using default JdbcTemplate instance since no JdbcTemplate instance object was associated with current active thread.",
//							e);
//			return defaultJdbcTemplate;
//		}
//	}
//
//	public Object execute(ConnectionCallback action) throws DataAccessException {
//		return getJdbcTemplate().execute(action);
//	}
//
//	public Object execute(StatementCallback action) throws DataAccessException {
//		return getJdbcTemplate().execute(action);
//	}
//
//	public void execute(String sql) throws DataAccessException {
//		getJdbcTemplate().execute(sql);
//	}
//
//	public Object query(String sql, ResultSetExtractor rse)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, rse);
//	}
//
//	public List query(String sql, RowCallbackHandler rch)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, rch);
//	}
//
//	public List query(String sql, RowMapper rowMapper)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, rowMapper);
//	}
//
//	public Object queryForObject(String sql, RowMapper rowMapper)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, rowMapper);
//	}
//
//	public Object queryForObject(String sql, Class requiredType)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, requiredType);
//	}
//
//	public Map queryForMap(String sql) throws DataAccessException {
//		return getJdbcTemplate().queryForMap(sql);
//	}
//
//	public long queryForLong(String sql) throws DataAccessException {
//		return getJdbcTemplate().queryForLong(sql);
//	}
//
//	public int queryForInt(String sql) throws DataAccessException {
//		return getJdbcTemplate().queryForInt(sql);
////		getJdbcTemplate().queryFor
//	}
//
//	public List queryForList(String sql, Class elementType)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql, elementType);
//	}
//
//	public List queryForList(String sql) throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql);
//	}
//
//	public SqlRowSet queryForRowSet(String sql) throws DataAccessException {
//		return getJdbcTemplate().queryForRowSet(sql);
//	}
//
//	public int update(String sql) throws DataAccessException {
//		return getJdbcTemplate().update(sql);
//	}
//
//	public int[] batchUpdate(String[] sql) throws DataAccessException {
//		return getJdbcTemplate().batchUpdate(sql);
//	}
//
//	public Object execute(PreparedStatementCreator psc,
//			PreparedStatementCallback action) throws DataAccessException {
//		return getJdbcTemplate().execute(psc, action);
//	}
//
//	public Object execute(String sql, PreparedStatementCallback action)
//			throws DataAccessException {
//		return getJdbcTemplate().execute(sql, action);
//	}
//
//	public Object query(PreparedStatementCreator psc, ResultSetExtractor rse)
//			throws DataAccessException {
//		return getJdbcTemplate().query(psc, rse);
//	}
//
//	public Object query(String sql, PreparedStatementSetter pss,
//			ResultSetExtractor rse) throws DataAccessException {
//		return getJdbcTemplate().query(sql, pss, rse);
//	}
//
//	public Object query(String sql, Object[] args, int[] argTypes,
//			ResultSetExtractor rse) throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, argTypes, rse);
//	}
//
//	public Object query(String sql, Object[] args, ResultSetExtractor rse)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, rse);
//	}
//
//	public List query(PreparedStatementCreator psc, RowCallbackHandler rch)
//			throws DataAccessException {
//		return getJdbcTemplate().query(psc, rch);
//	}
//
//	public List query(String sql, PreparedStatementSetter pss,
//			RowCallbackHandler rch) throws DataAccessException {
//		return getJdbcTemplate().query(sql, pss, rch);
//	}
//
//	public List query(String sql, Object[] args, int[] argTypes,
//			RowCallbackHandler rch) throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, argTypes, rch);
//	}
//
//	public List query(String sql, Object[] args, RowCallbackHandler rch)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, rch);
//	}
//
//	public List query(PreparedStatementCreator psc, RowMapper rowMapper)
//			throws DataAccessException {
//		return getJdbcTemplate().query(psc, rowMapper);
//	}
//
//	public List query(String sql, PreparedStatementSetter pss,
//			RowMapper rowMapper) throws DataAccessException {
//		return getJdbcTemplate().query(sql, pss, rowMapper);
//	}
//
//	public List query(String sql, Object[] args, int[] argTypes,
//			RowMapper rowMapper) throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, argTypes, rowMapper);
//	}
//
//	public List query(String sql, Object[] args, RowMapper rowMapper)
//			throws DataAccessException {
//		return getJdbcTemplate().query(sql, args, rowMapper);
//	}
//
//	public Object queryForObject(String sql, Object[] args, int[] argTypes,
//			RowMapper rowMapper) throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, args, argTypes, rowMapper);
//	}
//
//	public Object queryForObject(String sql, Object[] args, RowMapper rowMapper)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, args, rowMapper);
//	}
//
//	public Object queryForObject(String sql, Object[] args, int[] argTypes,
//			Class requiredType) throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, args, argTypes,
//				requiredType);
//	}
//
//	public Object queryForObject(String sql, Object[] args, Class requiredType)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForObject(sql, args, requiredType);
//	}
//
//	public Map queryForMap(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForMap(sql, args, argTypes);
//	}
//
//	public Map queryForMap(String sql, Object[] args)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForMap(sql, args);
//	}
//
//	public long queryForLong(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForLong(sql, args, argTypes);
//	}
//
//	public long queryForLong(String sql, Object[] args)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForLong(sql, args);
//	}
//
//	public int queryForInt(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForInt(sql, args, argTypes);
//	}
//
//	public int queryForInt(String sql, Object[] args)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForInt(sql, args);
//	}
//
//	public List queryForList(String sql, Object[] args, int[] argTypes,
//			Class elementType) throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql, args, argTypes);
//	}
//
//	public List queryForList(String sql, Object[] args, Class elementType)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql, args, elementType);
//	}
//
//	public List queryForList(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql, args, argTypes);
//	}
//
//	public List queryForList(String sql, Object[] args)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForList(sql, args);
//	}
//
//	public SqlRowSet queryForRowSet(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForRowSet(sql, args, argTypes);
//	}
//
//	public SqlRowSet queryForRowSet(String sql, Object[] args)
//			throws DataAccessException {
//		return getJdbcTemplate().queryForRowSet(sql, args);
//	}
//
//	public int update(PreparedStatementCreator psc) throws DataAccessException {
//		return getJdbcTemplate().update(psc);
//	}
//
//	public int update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder)
//			throws DataAccessException {
//		return getJdbcTemplate().update(psc, generatedKeyHolder);
//	}
//
//	public int update(String sql, PreparedStatementSetter pss)
//			throws DataAccessException {
//		return getJdbcTemplate().update(sql, pss);
//	}
//
//	public int update(String sql, Object[] args, int[] argTypes)
//			throws DataAccessException {
//		return getJdbcTemplate().update(sql, args, argTypes);
//	}
//
//	public int update(String sql, Object[] args) throws DataAccessException {
//		return getJdbcTemplate().update(sql, args);
//	}
//
//	public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss)
//			throws DataAccessException {
//		return getJdbcTemplate().batchUpdate(sql, pss);
//	}
//
//	public Object execute(CallableStatementCreator csc,
//			CallableStatementCallback action) throws DataAccessException {
//		return getJdbcTemplate().execute(csc, action);
//	}
//
//	public Object execute(String callString, CallableStatementCallback action)
//			throws DataAccessException {
//		return getJdbcTemplate().execute(callString, action);
//	}
//
//	public Map call(CallableStatementCreator csc, List declaredParameters)
//			throws DataAccessException {
//		return getJdbcTemplate().call(csc, declaredParameters);
//	}
//
//}
