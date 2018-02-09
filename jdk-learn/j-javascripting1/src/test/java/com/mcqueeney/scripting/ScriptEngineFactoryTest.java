package com.mcqueeney.scripting;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import junit.framework.TestCase;

public class ScriptEngineFactoryTest extends TestCase {

    /** 
     * Find all available ScriptEngineFactory objects and ask them what
     * script language they support.
     */
    public void testLoadedEngineFactories() {
        ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
        for (ScriptEngineFactory factory : scriptEngineMgr.getEngineFactories()) {
            System.out.println("Engine name: " + factory.getEngineName());
            System.out.println("Engine version: " + factory.getEngineVersion());
            System.out.println("Language name: " + factory.getLanguageName());
            System.out.println("Language version: " + factory.getLanguageVersion());
            System.out.println("Names this engine is known by:");
            for (String name : factory.getNames()) {
                System.out.println("\t" + name);
            }
            System.out.println("File extensions this engine knows how to handle:");
            for (String extension : factory.getExtensions()) {
                System.out.println("\t" + extension);
            }
            System.out.println("-----");
        }
    }
}
