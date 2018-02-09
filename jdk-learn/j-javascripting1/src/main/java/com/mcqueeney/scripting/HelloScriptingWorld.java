package com.mcqueeney.scripting;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloScriptingWorld {

	public static void main(String[] args) 
      throws ScriptException, NoSuchMethodException
    {
        ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
        ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
        if (jsEngine == null) {
            System.err.println("No script engine found for JavaScript");
            System.exit(1);
        }

        System.out.println("Calling invokeHelloScript...");
        invokeHelloScript(jsEngine);

        System.out.println("\nCalling defineScriptFunction...");
        defineScriptFunction(jsEngine);

        System.out.println("\nCalling invokeScriptFunctionFromEngine...");
        invokeScriptFunctionFromEngine(jsEngine);
        
        System.out.println("\nCalling invokeScriptFunctionFromJava...");
        invokeScriptFunctionFromJava(jsEngine);
        
        System.out.println("\nCalling invokeJavaFromScriptFunction...");
        invokeJavaFromScriptFunction(jsEngine);
    }

    private static void invokeHelloScript(ScriptEngine jsEngine)
      throws ScriptException
    {
        jsEngine.eval("println('Hello from JavaScript')");
    }

    private static void defineScriptFunction(ScriptEngine engine)
      throws ScriptException 
    {
        // Define a function in the script engine
        engine.eval(
            "function sayHello(name) {" +
            "    println('Hello, ' + name)" +
            "}"
        );
    }

    private static void invokeScriptFunctionFromEngine(ScriptEngine jsEngine)
      throws ScriptException
    {
      jsEngine.eval("sayHello('World!')");
    }

    private static void invokeScriptFunctionFromJava(ScriptEngine jsEngine)
      throws ScriptException, NoSuchMethodException
    {
        Invocable invocableEngine = (Invocable) jsEngine;
        invocableEngine.invokeFunction("sayHello", "from Java");
    }

    private static void invokeJavaFromScriptFunction(ScriptEngine jsEngine)
      throws ScriptException
    {
        jsEngine.put("helloScriptingWorld", new HelloScriptingWorld());
        jsEngine.eval(
            "println('Invoking getHelloReply method from JavaScript...');" +
            "var msg = helloScriptingWorld.getHelloReply('JavaScript');" +
            "println('Java returned: ' + msg)"
        );
    }
    
    /** Method invoked from the above script to return a string. */
    public String getHelloReply(String name) {
        return "Java method getHelloReply says, 'Hello, " + name + "'";
    }
}
