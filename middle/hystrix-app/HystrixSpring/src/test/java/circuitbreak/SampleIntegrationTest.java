//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration
//public class RemoteCallServiceHystrixTest {
//
//    @Autowired
//    private RemoteCallService remoteCallService;
//
//    @Test
//    public void testRemoteCall() throws Exception {
//        assertThat(this.remoteCallService.call("test"), is("FALLBACK: test"));
//        assertThat(this.remoteCallService.call("test"), is("FALLBACK: test"));
//        assertThat(this.remoteCallService.call("test"), is("test"));
//    }
//
//    @Configuration
//    @EnableAutoConfiguration
//    @EnableHystrix
//    public static class SpringConfig {
//
//        @Bean
//        public RemoteCallService remoteCallService() {
//            return new DummyRemoteCallService();
//        }
//    }
//}