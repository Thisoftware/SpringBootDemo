package domain;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author ：neil
 * @date ：Created in 22/12/22 18:51:36
 * @description：
 * @modified By：
 * @Version ：1.0
 */
@Slf4j
public class TranslationZnToEn {
    /**
     *  百度翻译app id
     */
    private static final String APP_ID = "";
    /**
     *  百度翻译key
     */
    private static final String SECURITY_KEY = "";
    private static final String sourceSimpleLanguage = "en";


    public static void main(String[] args) throws InterruptedException {
        // 如果代码中出现中文和英文夹在一起，可能会出现翻译偏差，代码翻译完之后，检查代码是否能启动
        // 指定好指定文件
        File fileBasic = new File("D:/translate/idn-operation-management");
        findFileTransaction(fileBasic);
        System.out.println("翻译文件夹下的所有java结束");

//        //demo 单个文件，直接指定好完整的文件相对路径
//        File javaFile = new File("/Users/neil/IdeaProjects/idn-channel/idn-channel-service/src/main/java/com/zongfan/idn/channel/payment/instamoney/InstamoneyNotifyServiceProcessor.java");
//        findFileTransaction(javaFile);
//        System.out.println("翻译指定文件结束");

//        TransApi api=new TransApi(APP_ID,SECURITY_KEY);
//        String translationResults= api.getTransResult("中国","zh","en");
//        System.out.println("translationResults:"+translationResults);


    }

    public static void findFileTransaction(File file) {
        if (file.isFile()) {
            translateDocuments(file);
        }
        File[] fs = file.listFiles();
        if (fs != null && fs.length > 0) {
            for (File f : fs) {
                if (f.isDirectory()) {
                    findFileTransaction(f);
                } else if (f.isFile()) {
                    translateDocuments(f);
                }
            }
        }
    }

    public static void translateDocuments(File file) {
        String encoding = "utf-8";
        if (!file.getName().contains(".html")) {
            return;
        }
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            if (file.isFile() && file.exists()) {
                String lineTxt;
                System.out.println("--->>> 开始翻译……"+file.getAbsolutePath());
                StringBuilder result = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (isChinese(lineTxt)) {
                        int firstZh = 0;
                        for (int j = 0; j < lineTxt.length(); j++) {
                            boolean chinese2 = isChinese2((char) lineTxt.charAt(j));
                            if (chinese2) {
                                firstZh = j;
                                break;
                            }
                        }

                        int lastZh = 0;
                        for (int j = lineTxt.length() - 1; j > 0; j--) {
                            boolean chinese2 = isChinese2((char) lineTxt.charAt(j));
                            if (chinese2) {
                                lastZh = j;
                                break;
                            }
                        }
                        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
                        String transResult = api.getTransResult(lineTxt.substring(firstZh, lastZh + 1), "zh", sourceSimpleLanguage);
                        System.out.println("翻译结果:"+transResult);
                        Map<String, Object> transMap = JSON.parseObject(transResult, Map.class);
                        String trans_result = transMap.get("trans_result").toString();
                        List<Map> transResultMap = JSON.parseArray(trans_result, Map.class);
                        lineTxt = lineTxt.substring(0, firstZh) + transResultMap.get(0).get("dst") + lineTxt.substring(lastZh + 1, lineTxt.length());
                    }
                    result.append(lineTxt + "\n");
                }
                writerFile(result.toString(), file);
                System.out.println("修改文件：" + file.getAbsolutePath() + " \n");
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            System.out.println(e);
        }
    }

    public static boolean isChinese(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese2(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChinese2(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    public static void writerFile(String s, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(s.getBytes());
            System.out.println("文件修改成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件修改失败！");
        }
    }
}
