package com.anxpp.io.calculator.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO�ͻ���
 *
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class ClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;

    public ClientHandle(String ip, int port) {
        this.host = ip;
        this.port = port;
        try {
            //����ѡ����
            selector = Selector.open();
            //�򿪼���ͨ��
            socketChannel = SocketChannel.open();
            //���Ϊ true�����ͨ��������������ģʽ�����Ϊ false�����ͨ���������ڷ�����ģʽ
            socketChannel.configureBlocking(false);//����������ģʽ
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //ѭ������selector
        while (started) {
            try {
                //�����Ƿ��ж�д�¼�������selectorÿ��1s������һ��
                selector.select(1000);
                //����,ֻ�е�����һ��ע����¼�������ʱ��Ż����.
//				selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //selector�رպ���Զ��ͷ�����������Դ
        if (selector != null)
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect())
                    ;
                else
                    System.exit(1);
            }
            //����Ϣ
            if (key.isReadable()) {
                //����ByteBuffer��������һ��1M�Ļ�����
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //��ȡ�������������ض�ȡ�����ֽ���
                int readBytes = sc.read(buffer);
                //��ȡ���ֽڣ����ֽڽ��б����
                if (readBytes > 0) {
                    //����������ǰ��limit����Ϊposition=0�����ں����Ի������Ķ�ȡ����
                    buffer.flip();
                    //���ݻ������ɶ��ֽ��������ֽ�����
                    byte[] bytes = new byte[buffer.remaining()];
                    //���������ɶ��ֽ����鸴�Ƶ��½���������
                    buffer.get(bytes);
                    String result = new String(bytes, "UTF-8");
                    System.out.println("�ͻ����յ���Ϣ��" + result);
                }
                //û�ж�ȡ���ֽ� ����
//				else if(readBytes==0);
                //��·�Ѿ��رգ��ͷ���Դ
                else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    //�첽������Ϣ
    private void doWrite(SocketChannel channel, String request) throws IOException {
        //����Ϣ����Ϊ�ֽ�����
        byte[] bytes = request.getBytes();
        //����������������ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //���ֽ����鸴�Ƶ�������
        writeBuffer.put(bytes);
        //flip����
        writeBuffer.flip();
        //���ͻ��������ֽ�����
        channel.write(writeBuffer);
        //****�˴���������д������Ĵ���
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port)))
            ;
        else
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void sendMsg(String msg) throws Exception {
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }
}