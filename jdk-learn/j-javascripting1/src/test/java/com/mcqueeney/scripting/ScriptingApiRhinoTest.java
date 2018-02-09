package com.mcqueeney.scripting;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import junit.framework.TestCase;

/**
 * Test key Java Scripting API features using built-in Rhino JavaScript engine.
 * 
 * @author Tom McQueeney
 */
public class ScriptingApiRhinoTest extends TestCase {

    /**
     * Instantiate the included Rhino JavaScript script engine directly
     * (rather than normally through the ScriptManager) to ensure there is
     * no global scope set. 
     * <p>
     * Note: this test works ONLY with Sun's JRE.
     */
    public void testNoGlobalScopeForDirectInstantiation() throws Exception {
        Class<?> c = Class.forName("com.sun.script.javascript.RhinoScriptEngine");
        ScriptEngine rhinoEngine = (ScriptEngine) c.newInstance();
        Bindings globalBindings = 
            rhinoEngine.getBindings(ScriptContext.GLOBAL_SCOPE);
        assertNull("No global bindings should be present", globalBindings);
    }

    /**
     * Instantiate the included Rhino JavaScript script engine through the 
     * ScriptManager and ensure there is a global scope. 
     */
    public void testGlobalScopeForManagedInstantiation() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript"); 
        Bindings globalBindings = 
            jsEngine.getBindings(ScriptContext.GLOBAL_SCOPE);

        assertNotNull("Global bindings should be present", globalBindings);
        
        // Global bindings returned by the engine should be the same as the
        // bindings returned by the engine manager.
        assertSame(
            "Engine manager has different global bindings", 
            globalBindings, engineManager.getBindings()
        );
        
        // Spec is silent on global bindings content, but we expect nothing.
        assertEquals(
            "Global bindings has seeded values!", 
            0, globalBindings.size()
        );
    }
    
    public void testEngineScopeBindings() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript"); 
        Bindings engineBindings = 
            jsEngine.getBindings(ScriptContext.ENGINE_SCOPE);

        assertNotNull("Engine bindings should be present", engineBindings);

        // Engine bindings probably empty, but we print it if not.
        for (String key : engineBindings.keySet()) {
            System.out.println(
                "Key='" + key + "', value='" + engineBindings.get(key) + "'"
            );
        }
        
        // Test that engine actually stores objects in bindings.
        int currentBindingsSize = engineBindings.size();
        jsEngine.put("testing", "123");
        assertEquals(
            "Bindings size should have increased by 1", 
            currentBindingsSize + 1, engineBindings.size()
        );
        assertEquals("123", jsEngine.get("testing"));
        assertSame(
            "Bindings should contain same object we put in it", 
            "123", jsEngine.get("testing")
        );
        assertSame(
            "Engine Bindings should give us same object as Engine", 
            "123", engineBindings.get("testing")
        );
    }
    
    /**
     * Tests methods on the ScriptContext.
     */
    public void testScriptContext() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript");
        
        Bindings engineBindings = 
            jsEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        Bindings globalBindings = 
            jsEngine.getBindings(ScriptContext.GLOBAL_SCOPE);

        engineBindings.put("testing", "123");
        globalBindings.put("testing", "345");
        
        ScriptContext context = jsEngine.getContext();
        assertSame(
            "Context should give us engine binding before global", 
            "123", context.getAttribute("testing")
        );
        assertSame(
            "Context's global scope has wrong value", 
            "345", context.getAttribute("testing", ScriptContext.GLOBAL_SCOPE)
        );
        
        assertTrue(
            "Engines created by manager should have global and engine scope",
            context.getScopes().size() >= 2
        );
        
        // Removing object in engine scope reveals object previously hidden 
        // in the global scope when retrieved by ScriptContext.
        engineBindings.remove("testing");
        assertSame(
            "Global object not revealed in context after deleting engine's object", 
            "345", context.getAttribute("testing")
        );
        
        // But remember, using ScriptEngine.get retrieves values *only*
        // from the engine scope, by definition. Check to verify:
        assertNull(
            "Engine sees global-scope object. API violation", 
            jsEngine.get("testing")
        );
        
        // See the testScopeAndBindingsState for more Bindings/context fun.
    }
    
    /**
     * Tests to determine whether a function defined in a script engine using
     * eval is available even if we change the ScriptContext or Bindings on
     * subsequent calls to eval.
     */
    public void testExecutionScope() throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript");
        
        jsEngine.eval(
            "function sayHello(name) {" +
            "    message = 'Hello, ' + name;" +
            "    return message" +
            "}"
        );
        
        assertEquals("Hello, Tom", jsEngine.eval("sayHello('Tom')"));
        assertEquals("Hello, Tom", jsEngine.get("message"));

        // Replace with empty engine bindings and ensure "message" isn't there.
        Bindings emptyBindings1 = jsEngine.createBindings();
        jsEngine.setBindings(emptyBindings1, ScriptContext.ENGINE_SCOPE);
        assertNull(
            "message var should not be in engine scope", 
            jsEngine.get("message")
        );
        // Ensure sayHello function isn't in scope anymore, either.
        try {
            jsEngine.eval("sayHello('there')");
            fail("sayHello method is still in the engine's context");
        } catch (ScriptException se) {
            // Good. Expected.
        }
    }
    
    public void testScopeAndBindingsState() throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript");
        
        // Like testExecutionScope, except move the function to global context.
        jsEngine.eval(
            "function sayHello(name) {" +
            "    message = 'Hello, ' + name;" +
            "    return message" +
            "}"
        );
        assertEquals("Hello, again", jsEngine.eval("sayHello('again')"));
        assertEquals("Hello, again", jsEngine.get("message"));

        Bindings bindingsWithFunction = 
            jsEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        Bindings emptyBindings = jsEngine.createBindings();
        jsEngine.setBindings(emptyBindings, ScriptContext.ENGINE_SCOPE);

        // Test that sayHello function no longer is in the engine scope.
        // Note spec does not require engines to maintain global functions
        // in the engine context. Rhino offers this feature, however.
        try {
            jsEngine.eval("sayHello('there')");
            fail("sayHello method is still in the engine's context");
        } catch (ScriptException se) {
            // Good. Expected.
        }
        
        // Add sayHello function in saved bindings to global scope and retest
        jsEngine.setBindings(bindingsWithFunction, ScriptContext.GLOBAL_SCOPE);

        // Note how functions are searched for in all scopes in Rhino engine.
        // Is this required by spec?
        // Spec does say in 4.3.4.1.3 that global bindings don't need to be 
        // accessible to scripts as variables.
        try {
            jsEngine.eval("sayHello('there again')");
        } catch (ScriptException se) {
            fail("sayHello should have been found in the global scope");
        }
        try {
            jsEngine.eval("sayHello('there yet again')", emptyBindings);
        } catch (ScriptException se) {
            fail("sayHello should exist in global scope even with empty bindings");
        }

        // Ensure variable 'message' is now in the correct scope.
        
        // ScriptEngine.get looks *only* in the engine scope. 
        assertNull("message shouldn't be in engine scope", jsEngine.get("message"));
        
        assertEquals(
            "message var *should* be in global scope",
            "Hello, there yet again", 
            jsEngine.getBindings(ScriptContext.GLOBAL_SCOPE).get("message")
        );

        assertEquals(
            "message var/attribute should be in global scope",
            ScriptContext.GLOBAL_SCOPE, 
            jsEngine.getContext().getAttributesScope("message")
        );

        // To search for variable in all scopes, use ScriptContext.getAttribute.
        assertEquals(
            "message var should be findable searching up thru the context",
            "Hello, there yet again", 
            jsEngine.getContext().getAttribute("message")
        );
    }
    
    /**
     * Tests for the existence of the required 'context' variable
     * script engines must set in their context containing the ScriptContext.
     * Spec 4.3.4.1.2
     */
    public void testContextAvailable() throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript");
        
        assertEquals(
            "Standard 'context' var pre-set in script context",
            ScriptContext.ENGINE_SCOPE,
            jsEngine.eval("context.ENGINE_SCOPE;")
        );

        jsEngine.eval("context.setAttribute('hi', 'there', context.ENGINE_SCOPE)");
        assertEquals(
            "'context' didn't allow attribute to be set",
            "there",
            jsEngine.get("hi")
        );

    }

    public void testEngineFactoryParameters() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine jsEngine = engineManager.getEngineByName("JavaScript");
        
        assertEquals(
            "Rhino no longer multithreaded",
            "MULTITHREADED",
            jsEngine.getFactory().getParameter("THREADING")
        );
    }
}
