package ansy;

import java.lang.reflect.Proxy;

public class RpcClientProxyBuilder {
    public static <T> ProxyBuilder<T> create(Class<T> targetClass) {
        return new ProxyBuilder<T>(targetClass);
    }

    public static class ProxyBuilder<T> {
        private Class<T> clazz;
        private RpcClient rpcClient;

        private long timeoutMills = 0;
        private RpcInvokeHook rpcInvokeHook = null;
        private String host;
        private int port;

        private ProxyBuilder(Class<T> clazz) {
            this.clazz = clazz;
        }

        /**
         * timeout time in mills. Set to 0 means no timeout and keep waiting for
         * result. Only works in synchronous way. (default 0)
         */
        public ProxyBuilder<T> timeout(long timeoutMills) {
            this.timeoutMills = timeoutMills;
            if (timeoutMills < 0)
                throw new IllegalArgumentException("timeoutMills can not be minus!");

            return this;
        }

        /**
         * set the RpcInvokeHook which will be invoke when the proxy object invoke
         * a method. (default null)
         */
        public ProxyBuilder<T> hook(RpcInvokeHook hook) {
            this.rpcInvokeHook = hook;
            return this;
        }

        /**
         * set the IP address and port of the RpcServer. Note that this method will
         * only set the value but do not connect immediately. Connection will be done in
         * build() or buildAsyncProxy().
         */
        public ProxyBuilder<T> connect(String host, int port) {
            this.host = host;
            this.port = port;
            return this;
        }

        /**
         * build the synchronous proxy.In synchronous way, Thread will be blocked until
         * get the result or timeout.
         */
        @SuppressWarnings("unchecked")
        public T build() {
            rpcClient = new RpcClient(timeoutMills, rpcInvokeHook, host, port);
            rpcClient.connect();

            return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, rpcClient);
        }

        /**
         * build the asynchronous proxy.In asynchronous way, a RpcFuture will be
         * return immediately.
         */
        public RpcClientAsyncProxy buildAsyncProxy() {
            rpcClient = new RpcClient(timeoutMills, rpcInvokeHook, host, port);
            rpcClient.connect();

            return new RpcClientAsyncProxy(rpcClient);
        }
    }
}  