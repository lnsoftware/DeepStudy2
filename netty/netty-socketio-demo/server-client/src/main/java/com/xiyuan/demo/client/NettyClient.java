package com.xiyuan.demo.client;

import com.xiyuan.demo.model.TestModel.Responce;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyClient {

    public NettyClient() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new MyChannelHandler());

        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9000).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    class MyChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
//			channel.pipeline().addLast(new LineBasedFrameDecoder(10240));
//			channel.pipeline().addLast(new StringDecoder());
//			channel.pipeline().addLast(new StringEncoder());

//			ByteBuf delimiter = Unpooled.copiedBuffer("__xy__".getBytes());
//			channel.pipeline().addLast(new DelimiterBasedFrameDecoder(10240, delimiter));
//			channel.pipeline().addLast(new StringDecoder());
//			channel.pipeline().addLast(new StringEncoder());

            channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
            channel.pipeline().addLast(new ProtobufDecoder(Responce.getDefaultInstance()));
            channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
            channel.pipeline().addLast(new ProtobufEncoder());

            channel.pipeline().addLast(new NettyClientHandler());
        }

    }

    public static void main(String[] args) {
        new NettyClient();
    }

}
