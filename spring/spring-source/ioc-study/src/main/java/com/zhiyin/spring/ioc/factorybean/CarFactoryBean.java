package com.zhiyin.spring.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2017/6/29.
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String name;

    @Override public Car getObject() throws Exception {
        Car car = new Car();
        car.setName(name);
        return car;
    }

    @Override public Class<?> getObjectType() {
        return Car.class;
    }

    @Override public boolean isSingleton() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
