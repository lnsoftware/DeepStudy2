package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * Created by hg on 2017/12/21.
 */
@SpringBootApplication
public class BootShutdown {

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(BootShutdown.class);
        app.addListeners(new MyApplicationStartedEventListener());

        app.addListeners(new ApplicationListener<ContextClosedEvent>() {

            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.println("shutdown");
            }
        });

        app.addListeners(new ApplicationPidFileWriter("./bin/app.pid"));


        app.run(args);

    }

}
