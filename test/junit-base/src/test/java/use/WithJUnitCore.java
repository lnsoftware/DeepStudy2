package use;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class WithJUnitCore
{
    public static void main(String[] args)
	{
		List<Class> testCases = new ArrayList();

		//Add test cases
		testCases.add(TestFeatureOne.class);
		testCases.add(TestFeatureTwo.class);

		for (Class testCase : testCases)
               {
                    runTestCase(testCase);
               }
	}

    private static void runTestCase(Class testCase)
    {
        Result result = JUnitCore.runClasses(testCase);
        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }
    }
}