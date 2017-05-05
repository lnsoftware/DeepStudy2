package com.xiyuan.demo.netty;

import com.xiyuan.demo.dispatchcenter.DispatchCenter;
import com.xiyuan.demo.model.TestModel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {

    public NettyServer() {
        //第1步	创建工作组EventLoopGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //第2步	创建服务器引导ServerBootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 10240)//设置缓冲区长度
                .handler(new LoggingHandler(LogLevel.INFO));

        //第3步	添加Channel监听和处理handler
        serverBootstrap.childHandler(new MyChannelHandler());

        //4、5.绑定监听端口，等待服务器关闭并释放资源
        try {
            ChannelFuture f = serverBootstrap.bind(9000).sync();//绑定9000这个端口
            f.channel().closeFuture().sync();//sync会导致线程阻塞,使得服务器在一个无限循环中一直运行，知道出现未捕获的异常或者手动关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    class MyChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            //解码器
//			ByteBuf delimiter = Unpooled.copiedBuffer("__xy__".getBytes());
//			channel.pipeline().addLast(new DelimiterBasedFrameDecoder(10240, delimiter));//10240指消息最大长度，读取一条消息超过1024bt后还没有遇到分隔符，则报错
//			channel.pipeline().addLast(new StringDecoder());
//			channel.pipeline().addLast(new StringEncoder());

            //行解码器
//			channel.pipeline().addLast(new LineBasedFrameDecoder(10240));//这里的长度不超过.option(ChannelOption.SO_BACKLOG, 10240)中设置的长度
//			channel.pipeline().addLast(new StringDecoder());
//			channel.pipeline().addLast(new StringEncoder());

            // 定长解码器( 几乎不用)
//			channel.pipeline().addLast(new FixedLengthFrameDecoder(10240));//这里的长度不超过.option(ChannelOption.SO_BACKLOG, 10240)中设置的长度
//			channel.pipeline().addLast(new StringDecoder());
//			channel.pipeline().addLast(new StringEncoder());

            channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
            channel.pipeline().addLast(new ProtobufDecoder(TestModel.Request.getDefaultInstance()));
            channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
            channel.pipeline().addLast(new ProtobufEncoder());

            channel.pipeline().addLast(new NettyServerHandler());
        }
    }

    public static void main(String[] args) {
        DispatchCenter.instance();
        new NettyServer();
    }
}
