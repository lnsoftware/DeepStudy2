package com.zhiyin.queue.api.comsumer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/queue")
public class QueueConsumerController {

    private final AtomicLong counter = new AtomicLong();


}
