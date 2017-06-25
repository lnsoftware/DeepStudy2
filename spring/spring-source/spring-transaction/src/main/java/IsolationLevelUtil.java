import org.springframework.jdbc.core.JdbcTemplate;

public class IsolationLevelUtil {

	private static final ThreadLocal threadJdbcTemplate = new ThreadLocal();

	private IsolationLevelUtil() {
		super();
	}

	public static JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) threadJdbcTemplate.get();
		return jdbcTemplate;
	}

	public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		threadJdbcTemplate.set(jdbcTemplate);
	}

}
