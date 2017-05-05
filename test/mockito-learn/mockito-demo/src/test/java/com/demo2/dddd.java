package com.demo2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by hg on 2016/11/15.
 */
public class dddd {

    @InjectMocks
    private MainService mainService;

    @Mock
    private Service2 service2;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void testMe(){
        mainService.doMain();

        verify(service2).doSomethingAnother();

    }
}
