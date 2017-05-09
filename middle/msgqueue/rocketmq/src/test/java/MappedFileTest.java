import java.io.IOException;
import org.apache.rocketmq.store.MappedFile;
import org.apache.rocketmq.store.SelectMappedBufferResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MappedFileTest {
    private final String storeMessage = "Once, there was a chance for me!";

    @Test
    public void testSelectMappedBuffer() throws IOException {
        MappedFile mappedFile = new MappedFile("target/unit_test_store/MappedFileTest/000", 1024 * 64);
        boolean result = mappedFile.appendMessage(storeMessage.getBytes());
        assertThat(result).isTrue();


//        mappedFile.commit(storeMessage.getBytes().length);

//        SelectMappedBufferResult selectMappedBufferResult = mappedFile.selectMappedBuffer(0);
//        byte[] data = new byte[storeMessage.length()];
//        selectMappedBufferResult.getByteBuffer().get(data);
//        String readString = new String(data);
//
//        assertThat(readString).isEqualTo(storeMessage);
//
//        mappedFile.shutdown(1000);
//        assertThat(mappedFile.isAvailable()).isFalse();
//        selectMappedBufferResult.release();
//        assertThat(mappedFile.isCleanupOver()).isTrue();
//        assertThat(mappedFile.destroy(1000)).isTrue();
    }
}
