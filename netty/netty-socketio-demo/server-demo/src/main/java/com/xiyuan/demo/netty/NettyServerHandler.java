package com.xiyuan.demo.netty;

import com.xiyuan.demo.dispatchcenter.DispatchCenter;
import com.xiyuan.demo.model.TestModel.Request;
import com.xiyuan.demo.model.TestModel.Responce;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyServerHandler extends ChannelHandlerAdapter {

    private static final String LINE_END = System.getProperty("line.separator");

    private DispatchCenter dispatchCenter = DispatchCenter.instance();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        dispatchCenter.addChannel(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        dispatchCenter.removeChannel(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Request request = (Request) msg;
        if (request != null && request.getId() != null && request.getDeviceId() != null && request.getMethod() != null) {
            String deviceId = request.getDeviceId();
            if (deviceId.equals("")) {
                return;
            }

            if ("bindDeviceAndChannel".equals(request.getMethod())) {
                //特殊操作，绑定设备和channel
                dispatchCenter.bandDeviceAndChannel(deviceId, ctx.channel());

                //这里可以向客户端返回一些信息表示已经绑定成功，比如所有请求的地址列表
                Responce responce = Responce.newBuilder()
                        .setId(request.getId())
                        .setSuccess(true)
                        .setMsg("设备和通道绑定成功")
                        .build();
                ctx.channel().writeAndFlush(responce);
            } else if (dispatchCenter.getChannelByDevice(deviceId) != null) {
                //处理用户的其他请求，请求的派发在这里完成，通过method字段来派发给不同的controller的特定的方法处理
                dispatchCenter.dispatch(request);
            }
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
