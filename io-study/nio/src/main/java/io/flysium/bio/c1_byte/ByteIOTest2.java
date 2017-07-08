package io.flysium.bio.c1_byte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 字节输入输出流 - 字节数组
 *
 * @author SvenAugustus(蔡政滦)  e-mail: SvenAugustus@outlook.com
 * @version 2017年1月23日
 */
public class ByteIOTest2 {

    public static void main(String[] args) throws IOException {
        byte[] source = new byte[256];
        new Random().nextBytes(source);

        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();

            os.write(source);

            System.out.println(new String(os.toByteArray(), "utf-8"));
        } finally {
            if (os != null) {
                os.close();// 关闭流
            }
        }

        java.io.InputStream is = null;
        try {
            is = new ByteArrayInputStream(source);

            int data = -1;
            while ((data = is.read()) != -1) {
                //操作数据
                System.out.print((char) data);
            }
        } finally {
            if (is != null) {
                is.close();// 关闭流
            }
        }
    }

}
