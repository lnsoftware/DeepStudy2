package com.xiyuan.demo.client;

import com.xiyuan.demo.model.TestModel.Request;
import com.xiyuan.demo.model.TestModel.Responce;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class NettyClientHandler extends ChannelHandlerAdapter {

    private static final String LINE_END = System.getProperty("line.separator");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //channel连接成功，马上发起一个绑定设备和channel的请求
        Request request = Request.newBuilder()
                .setId("bindDeviceAndChannel_0")
                .setDeviceId("myDeviceId")
                .setUserId("")//用户尚未登录，传空字符串，不能不设值，否则会报错
                .setMethod("bindDeviceAndChannel")
                .build();
        ctx.channel().writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        super.channelRead(ctx, msg);
        System.out.println(msg);
        Responce responce = (Responce) msg;
        if (responce.getId().equals("bindDeviceAndChannel_0")) {
            //发起一个测试请求
            Request request = Request.newBuilder()
                    .setId("" + new Date().getTime() + "_" + (int) (Math.random() * 10000))
                    .setDeviceId("myDeviceId")
                    .setUserId("")//用户尚未登录，传空字符串，不能不设值，否则会报错
                    .setMethod("test")
                    .build();
            ctx.channel().writeAndFlush(request);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
