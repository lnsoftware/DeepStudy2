package io.flysium.nio.c3_selector;

import io.flysium.nio.ByteBufferUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * ServerSocketChannel示例，使用Selector模式
 *
 * @author Sven Augustus
 */
public class ServerSocketChannelTest2 {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        ServerSocketChannel channel = null;
        Selector selector = null;
        try {
            // 打开 ServerSocketChannel
            channel = ServerSocketChannel.open();
            // 设置非阻塞模式，read的时候就不再阻塞
            channel.configureBlocking(false);
            // 将 ServerSocket 绑定到特定地址（IP 地址和端口号）
            channel.bind(new InetSocketAddress("127.0.0.1", 9595));

            // 创建Selector选择器
            selector = Selector.open();
            // 注册事件，监听客户端连接请求
            channel.register(selector, SelectionKey.OP_ACCEPT);

            final int timeout = 1000;//超时timeout毫秒
            while (true) {
                if (selector.select(timeout) == 0) {//无论是否有事件发生，selector每隔timeout被唤醒一次
                    continue;
                }
                Set selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    if (key.isAcceptable()) {// 接收就绪，server channel成功接受到一个连接。
                        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                        socketChannel.configureBlocking(false);// 设置非阻塞模式
                        // 注册读操作 , 以进行下一步的读操作
                        socketChannel.register(key.selector(), SelectionKey.OP_READ);
                    } else if (key.isConnectable()) {//连接就绪，channel成功连接另一个服务器。

                    } else if (key.isReadable()) {//读就绪，channel通道中有数据可读。
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //System.out.println("准备读：");
                        // 读取客户端发送的数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int readBytes = socketChannel.read(buffer);
                        if (readBytes >= 0) {// 非阻塞，立刻读取缓冲区可用字节
                            Object object = ByteBufferUtils.readObject(buffer);
                            //System.out.println(object);
                            //附加参数
                            key.attach(object);
                            // 切换写操作 , 以进行下一步的写操作
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else if (readBytes < 0) { //客户端连接已经关闭，释放资源
                            System.out.println("客户端" + socketChannel.socket().getInetAddress() + "端口" + socketChannel.socket().getPort() + "断开...");
                            socketChannel.close();
                        }
                    } else if (key.isValid() && key.isWritable()) {//写就绪，channel通道等待写数据。
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 计算
                        Integer integer = Integer.parseInt(String.valueOf(key.attachment()));
                        String serializable = String.valueOf(integer * 2);
                        // 往客户端写数据
                        ByteBuffer byteBuffer = ByteBufferUtils.writeObject(serializable);
                        socketChannel.write(byteBuffer);
                        System.out.println("客户端服务器：" + integer + "，响应：" + serializable);
                        // 切换读操作 , 以进行下一次的接口请求，即下一次读操作
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } finally {
            //关闭 ServerSocketChannel
            if (channel != null) {
                channel.close();
            }
            if (selector != null) {
                selector.close();
            }
        }
    }

}
