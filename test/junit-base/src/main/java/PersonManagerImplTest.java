import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

@RunWith(SwordSpringJUnit4ClassRunner.class)
@TestExecutionListeners({SwordTestExecutionListener.class})
public class PersonManagerImplTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @SwordTestLogger(log = "GET PERSON TEST!")
    @SwordTestData(tedaCaseId = 1L)
    public void testGetPerson() {
        PersonManagerImpl personManagerImpl = new PersonManagerImpl();
        List<Person> result = personManagerImpl.getPerson(27);
        System.out.println(result);
    }
}