package disconnection;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfConverterUtil {

    @Test
    public void testExpire(){
        long l = 5 * 365 * 24 * 60 * 60L;
        long expireEndTime = System.currentTimeMillis() + l * 1000;
        Date expiration = new Date(expireEndTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(expiration));
    }

    public static void getImage() throws IOException {
        String imgData = "data:image/png;base64,iVBORw0KG";
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream("D:\\app\\ab.jpg");
            // Base64解码
            imgData = imgData.replace("data:image/png;base64,","");
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

    public static String GetImageStr(){
        String imgFile = "D:\\app\\sign.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    public static void main(String[] args) {
//        System.out.println(GetImageStr());
        try {
            getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
