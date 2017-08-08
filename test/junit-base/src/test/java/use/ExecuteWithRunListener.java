package use;

import org.junit.runner.JUnitCore;

public class ExecuteWithRunListener
{
    public static void main(String[] args)
	{
		JUnitCore runner = new JUnitCore();
		//Adding listener here
		runner.addListener(new ExecutionListener());
		runner.run(TestFeatureOne.class, TestFeatureTwo.class);
	}
}
