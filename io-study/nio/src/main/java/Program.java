public class Program {

    static private final int start = 0;
    static private final int size = 1024;

    static public void main(String args[]) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,
            start, size);

        mbb.put(0, (byte) 97);
        mbb.put(1023, (byte) 122);

        raf.close();
    }

    public void readOnly() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 缓冲区中的数据0-9
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        // 创建只读缓冲区
        ByteBuffer readonly = buffer.asReadOnlyBuffer();

        // 改变原缓冲区的内容
        for (int i = 0; i < buffer.capacity(); ++i) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readonly.position(0);
        readonly.limit(buffer.capacity());

        // 只读缓冲区的内容也随之改变
        while (readonly.remaining() > 0) {
            System.out.println(readonly.get());
        }
    }
}
