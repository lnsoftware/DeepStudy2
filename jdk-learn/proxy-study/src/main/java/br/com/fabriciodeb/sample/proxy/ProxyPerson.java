package br.com.fabriciodeb.sample.proxy;

import br.com.fabriciodeb.sample.Person;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyPerson implements InvocationHandler {

    private Person person;

    public ProxyPerson(Person person) {
        this.person = person;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before: " + person + " - " + method);

        method.invoke(person, args);

        System.out.println("after: " + person + " - " + method);

        return null;
    }

}
