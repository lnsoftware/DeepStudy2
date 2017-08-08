package com.zhiyin.thrift.example.service;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloClientDemo {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;

    public void startClient(String userName) throws IOException {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(
                    protocol);
            transport.open();
            String result = client.hello(userName);
            System.out.println("Thrify client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    public void startClient2(String userName) {
        TNonblockingTransport transport = null;
        try {
            transport = new TNonblockingSocket("127.0.0.1", 9160);
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
            HelloWorldService.AsyncClient asyncClient = new HelloWorldService.AsyncClient(protocolFactory,clientManager,transport);
            transport.open();
            asyncClient.hello(userName, new AsyncMethodCallback<String>() {
                @Override
                public void onComplete(String response) {
                    System.out.println(response);
                }

                @Override
                public void onError(Exception exception) {

                }
            });

            TimeUnit.SECONDS.sleep(2);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        HelloClientDemo client = new HelloClientDemo();
        client.startClient("Vilarsail");
    }
}
