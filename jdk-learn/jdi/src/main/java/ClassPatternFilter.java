import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class ClassPatternFilter {
	private static String SEPARATOR = ";";
	private static String EXCLUDED_CLASS_PATTERN = "ExcludedClassPattern";
	private static String[] excludes =new String[0];
	static
	{
		Properties configPro = new Properties();
		try {
			configPro.load(new FileInputStream(ExceptionFilter.class.getResource("").getPath().substring(1)+"ClassExcludeConfig.properties"));
			String excludeString = (String) configPro.get(EXCLUDED_CLASS_PATTERN);
			excludes = excludeString.split(SEPARATOR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] getExcludes()
	{
		return excludes;
	}
}
