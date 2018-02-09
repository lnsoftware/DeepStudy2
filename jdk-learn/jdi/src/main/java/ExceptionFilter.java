import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;


public class ExceptionFilter {
	private static Hashtable<String,String> allowedHashTable = new Hashtable<String,String>(); 
	private static String SEPARATOR = ";";
	private static String EXCEPTION_LIST_NAME_KEY = "ExceptionName";
	static
	{
		Properties configPro = new Properties();
		try {
			configPro.load(new FileInputStream(ExceptionFilter.class.getResource("").getPath().substring(1)+"ExceptionConfig.properties"));
			String exceptionString = (String) configPro.get(EXCEPTION_LIST_NAME_KEY);
			String[] exceptions = exceptionString.split(SEPARATOR);
			if(exceptions!=null && exceptions.length>0)
			{
				for(int i=0;i<exceptions.length;i++)
				{
					String exception = exceptions[i];
					allowedHashTable.put(exception, "");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isAllowedException(String exception)
	{
		if(allowedHashTable.containsKey(exception))
		{
			return true;
		}
		return false;
	}
}
