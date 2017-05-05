//package top.down;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.util.concurrent.EventExecutorGroup;
//
///**
// * Created by hg on 2017/4/28.
// */
//public class Demo2 {
//
//    public static void main(String[] args) {
//        // 指定mainReactor
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        // 指定subReactor
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        // 用户自定义的ThreadPool
//        EventExecutorGroup threadPool = new ThreadPool();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 100) // 设置TCP参数
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        public void initChannel(SocketChannel ch) throws Exception {
//                            ChannelPipeline p = ch.pipeline();
//                            p.addLast(threadPool,
//                                    new DecoderHandler(),   // 解码处理器
//                                    new ComputeHandler());  // 计算处理器
//                            new EncoderHandler(),   // 编码处理器
//                        }
//                    });
//
//            // 绑定到本地端口等待客户端连接
//            ChannelFuture f = b.bind(PORT).sync();
//
//            // 等待接受客户端连接的Channel被关闭
//            f.channel().closeFuture().sync();
//        } finally {
//            // 关闭两个线程组
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//            threadPool.shutdown();
//        }
//
//    }
//}
