package com.xiyuan.demo.controller;

import com.xiyuan.demo.dispatchcenter.BaseController;
import com.xiyuan.demo.dispatchcenter.DispatchMethod;
import com.xiyuan.demo.model.TestModel.Request;
import com.xiyuan.demo.model.TestModel.Responce;
import io.netty.channel.Channel;

public class TestController extends BaseController {

    @DispatchMethod(path = "test")
    public void test(Request request) {
        Responce responce = Responce.newBuilder()
                .setId(request.getId())
                .setSuccess(true)
                .setMsg("测试成功")
                .build();
        Channel channel = (Channel) dispatchCenter.getChannelByDevice(request.getDeviceId());
        if (channel != null) {
            channel.writeAndFlush(responce);
        }
    }

}
