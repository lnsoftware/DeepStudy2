import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 激活channel
        System.out.println("channelActive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // 断点
        System.out.println("channelRegistered");
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 断点
        System.out.println("handlerAdded");
    }
}
