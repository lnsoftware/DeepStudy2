package com.anxpp.io.calculator.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO�����Դ��__α�첽I/O
 *
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public final class ServerBetter {
    //Ĭ�ϵĶ˿ں�
    private static int DEFAULT_PORT = 12345;
    //������ServerSocket
    private static ServerSocket server;
    //�̳߳� ����ʽ�ĵ���
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);

    //���ݴ���������ü����˿ڣ����û�в����������·�����ʹ��Ĭ��ֵ
    public static void start() throws IOException {
        //ʹ��Ĭ��ֵ
        start(DEFAULT_PORT);
    }

    //����������ᱻ�����������ʣ���̫��Ҫ����Ч�ʣ�ֱ�ӽ��з���ͬ��������
    public synchronized static void start(int port) throws IOException {
        if (server != null)
            return;
        try {
            //ͨ�����캯������ServerSocket
            //����˿ںϷ��ҿ��У�����˾ͼ����ɹ�
            server = new ServerSocket(port);
            System.out.println("���������������˿ںţ�" + port);
            Socket socket;
            //ͨ������ѭ�������ͻ�������
            //���û�пͻ��˽��룬��������accept�����ϡ�
            while (true) {
                socket = server.accept();
                //�����µĿͻ��˽���ʱ����ִ������Ĵ���
                //Ȼ�󴴽�һ���µ��̴߳�������Socket��·
                executorService.execute(new ServerHandler(socket));
            }
        } finally {
            //һЩ��Ҫ��������
            if (server != null) {
                System.out.println("�������ѹرա�");
                server.close();
                server = null;
            }
        }
    }
}