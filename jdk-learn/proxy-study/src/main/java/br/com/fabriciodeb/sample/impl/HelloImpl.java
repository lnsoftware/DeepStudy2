package br.com.fabriciodeb.sample.impl;

import br.com.fabriciodeb.sample.IHello;

public class HelloImpl implements IHello {
    @Override
    public void hello() {
        System.out.println("HelloImpl: Hello World.");
    }
}
