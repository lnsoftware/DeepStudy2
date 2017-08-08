# Byte Buddy Examples

This are examples for Byte Buddy presentation for Motorola Tech Talks 2016 in Kraków.
The project includes three java agents and example of definition of a new class using Byte Buddy.

Agents prepared here are not intended to be loaded on the running application.
They can only be loaded with -javaagent command-line parameter, during virtual machine startup.

To see an example of agent that is capable for loading on the running VM, check [that project](https://github.com/jakubhalun/tt2016_byte_buddy_agent_demo).

## Execution

### New class definition example

```
java -jar byte-buddy-examples.jar
```

This will execute the code defined in [ClassCreationExample](https://github.com/jakubhalun/tt2016_byte_buddy_examples/blob/master/src/main/java/pl/halun/demo/bytebuddy/plain/examples/ClassCreationExample.java) class.

New class is defined using Byte Buddy. It implements *Runnable* interface. Object of a new class is instantiated and executed in separate thread.

### HelloWorldAgent

```
java -javaagent:byte-buddy-examples.jar="HelloWorldAgent" -jar any-application.jar
```

Loads agent that changes *toString* implementation in all classes to return value "Hello World!" 

### TryCatchAgent

```
java -javaagent:byte-buddy-examples.jar="TryCatchAgent className methodName" -jar any-application.jar
```

Loads agent that encloses method invocation into try-catch block, logs any caught exception and then re-throws it.
Name of a class and name of a method must be provided, as the second and third agent parameter respectively.

### RegexSelectionLoggerAgent

```
java -javaagent:byte-buddy-examples.jar="RegexSelectionLoggerAgent classNameRegex methodNameRegex" -jar any-application.jar
```

Loads agent that adds logs to classes and methods selected using regular expressions.
Name of a class and name of a method must be provided, as the second and third agent parameter respectively.

