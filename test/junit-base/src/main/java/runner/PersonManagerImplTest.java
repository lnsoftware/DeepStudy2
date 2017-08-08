//package runner;
//
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.TestExecutionListeners;
//
//@RunWith(SwordSpringJUnit4ClassRunner.class)
//@TestExecutionListeners({ SwordTestExecutionListener.class })
//public class PersonManagerImplTest {
//@BeforeClass
//public static void setUpBeforeClass() throws Exception {
//}
//@AfterClass
//public static void tearDownAfterClass() throws Exception {
//}
//@Before
//public void setUp() throws Exception {
//}
//@After
//public void tearDown() throws Exception {
//}
//@Test
//@SwordTestLogger(log = "GET PERSON TEST!")
//@SwordTestData(tedaCaseId = 1L)
//public void testGetPerson() {
//PersonManagerImpl personManagerImpl = new PersonManagerImpl();
//List<Person> result = personManagerImpl.getPerson(27);
//System.out.println(result);
//}
//}