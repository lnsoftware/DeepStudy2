import exceptions.UserDefinedException;


class MainThread {
	int a = 0, b = 1, c = 2;

	public MainThread()
	{
		
	}

	public static void main(String[] args) {
		int e = 4;
		int g = 5;
		MainThread mt = new MainThread();
		//simulate exception scenario
		for(int i=0; i<2; i++)
		{
			try {
				if(i == 0)
				{
					//user defined exception
					mt.makeABusinessException(i);
				}
				if(i == 1)
				{
					//null pointer exception
					throw new NullPointerException();
				}
			} catch (Exception exception) {
				//do nothing
			}
		}	
		Thread thread1 = new Thread(new CounterThread("thread1"));
		Thread thread2 = new Thread(new CounterThread("thread2"));
		thread1.start();
		thread2.start();
	}


	public void makeABusinessException(int i) throws Exception {
		int d = 3;
		d = 4;
		throw new UserDefinedException("User Defined Exception");
	}
}