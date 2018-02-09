package com.mcqueeney.scripting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SimpleInvokeScript {

	public static void main(String[] args) 
      throws FileNotFoundException, ScriptException, NoSuchMethodException
    {
		new SimpleInvokeScript().run(); 
	}

    public void run() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
        ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
        if (jsEngine == null) {
            System.err.println("No script engine found for JavaScript");
            System.exit(1);
        }

        jsEngine.put("myJavaApp", this);
        
        File scriptFile = new File("src/main/scripts/SimpleJavaScript.js");
        
        // Capture the script engine's stdout in a StringWriter. 
        StringWriter scriptOutput = new StringWriter();
        jsEngine.getContext().setWriter(new PrintWriter(scriptOutput));
        
        System.out.println("Calling 'eval' on the script file");
        Object scriptReturnValue = jsEngine.eval(new FileReader(scriptFile));
        
        if (scriptReturnValue == null) {
            System.out.println("Script returned null");
        } else {
            System.out.println(
                "Script returned type " + scriptReturnValue.getClass().getName() +
                ", with string value '" + scriptReturnValue + "'"
            );
        }
        
        Invocable invocableEngine = (Invocable) jsEngine;
        System.out.println("Calling 'invokeFunction' on the engine");
        invocableEngine.invokeFunction("scriptFunction", "arg is 42");
        
        System.out.println("Script output:\n----------\n" + scriptOutput);
        System.out.println("----------");
        
        System.out.println("Engine name is: " + jsEngine.getFactory().getEngineName());
        System.out.println("Names this script engine is known by:");
        for (String name : jsEngine.getFactory().getNames()) {
            System.out.println("\t" + name);
        }
    }
    
    /** Method to be invoked from the script to return a string. */
    public String getSpecialMessage() {
        System.out.println("Java method 'getSpecialMessage' invoked");
        return "A special announcement from SimpleInvokeScript Java app";
    }
}
