import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Trace;
import com.dianping.cat.message.Transaction;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.junit.Test;

public class CatTest {

	@Test
	public void test() throws InterruptedException {

		for (int i = 0; i < 50; i++) {
			Transaction t = Cat.newTransaction("logTransaction", "logTransaction");
			Cat.newEvent("logEvent", "logEvent");
			t.setStatus(Message.SUCCESS);
			t.complete();
		}
		TimeUnit.SECONDS.sleep(3);
	}
}
