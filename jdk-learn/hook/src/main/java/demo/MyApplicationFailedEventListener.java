package demo;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable throwable = event.getException();
        handleThrowable(throwable);
    }

    /*处理异常*/
    private void handleThrowable(Throwable throwable) {
    }

}