
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author dzh
 *
 */
public class MapFile1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File tempFile =File.createTempFile("tempFile", null);
			RandomAccessFile raf =new RandomAccessFile(tempFile,"rw");
			FileChannel fc =raf.getChannel();
			
			//initialize file's content
			ByteBuffer buffer =ByteBuffer.allocate(100);
			buffer.put("The whether has been bad these day in GuangZhou".getBytes());
			buffer.flip();
			fc.write(buffer);
			
			MappedByteBuffer mbb0 =fc.map(MapMode.PRIVATE, 0, fc.size());
			MappedByteBuffer mbb1 =fc.map(MapMode.PRIVATE, 0, fc.size());
			
			System.out.println("Change mbb0 buffer");
			mbb0.position(4);
			mbb0.put("dzh".getBytes());
			showBuffer("mbb0",mbb0); //The dzhther has been bad these day in GuangZhou
			showBuffer("mbb1",mbb1); //The whether has been bad these day in GuangZhou
			
			mbb0.force(); //no effect
			MappedByteBuffer content =fc.map(MapMode.PRIVATE, 0, fc.size());
			showBuffer("file content", content); //The whether has been bad these day in GuangZhou
			
			raf.close();
			tempFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出buffer的内容
	 * @param name
	 * @param buffer
	 */
	private static void showBuffer(String name,MappedByteBuffer buffer){
		System.out.println(name+":");
		for(int i=0;i<buffer.limit();i++){
			char c =(char) buffer.get(i);
			System.out.print(c);
		}
		System.out.println(" ");
	}

}