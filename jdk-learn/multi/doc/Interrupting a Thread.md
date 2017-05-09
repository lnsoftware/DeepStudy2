If any thread is in sleeping or waiting state (i.e. sleep() or wait() is invoked), 
calling the interrupt() method on the thread, breaks out the sleeping or waiting state throwing InterruptedException. 
If the thread is not in the sleeping or waiting state, 
calling the interrupt() method performs normal behaviour and doesn't interrupt the thread but sets the interrupt flag to true. 
Let's first see the methods provided by the Thread class for thread interruption.



https://www.javatpoint.com/interrupting-a-thread