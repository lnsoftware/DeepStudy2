package com.zhiyin.spring.ioc.dep;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2017/6/29.
 */
//@Component
public class CarFactoryBean implements FactoryBean<Car> {
    @Override public Car getObject() throws Exception {
        Car car = new Car();
        car.setName("BM");
        return car;
    }

    @Override public Class<?> getObjectType() {
        return Car.class;
    }

    @Override public boolean isSingleton() {
        return false;
    }
}

class Car{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
