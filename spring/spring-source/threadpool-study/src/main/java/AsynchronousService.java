import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AsynchronousService {
 
    @Autowired
    private ApplicationContext applicationContext;
 
    @Autowired
    private TaskExecutor taskExecutor;
 
    public void executeAsynchronously() {
 
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //TODO add long running task
            }
        });
    }
}