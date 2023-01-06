package disconnection;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;


/**
 * @author: wust
 * @date: 2022/1/7 17:34
 * @description:
 */
@Slf4j
public class PdfGenerator {
    private  static final String FILE_PATH = "/contracttemplate/";
    private  static final String ENCODING = "UTF-8";
    private static final Configuration freemarkerConfig =  new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String templateName) {

        Writer out = new StringWriter();
        try {
            freemarkerConfig.setDefaultEncoding(ENCODING);
            freemarkerConfig.setClassForTemplateLoading(PdfGenerator.class, FILE_PATH);
            // 获取模板,并设置编码方式
            Template template = freemarkerConfig.getTemplate(templateName);
            // 合并数据模型与模板
            //将合并后的数据和模板写入到流中，这里使用的字符流
            template.process(data, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            log.error("合同模板组装异常", e);
            throw new RuntimeException("合同模板组装异常");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                log.error("文件流关闭异常", ex);
            }
        }
    }

    public static ByteArrayOutputStream exportPdf(Map<String, Object> data, String templateName) {
        String content = freeMarkerRender(data, templateName);
        Document document = new Document();
        PdfWriter writer = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            fontImp.register(templateName);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes()), null, Charset.forName(ENCODING), fontImp);
            outputStream.flush();
            return outputStream;
        } catch (Exception e) {
            log.error("合同转换pdf异常", e);
            throw new RuntimeException("合同转换pdf异常");
        } finally {
            document.close();
            if(writer != null) {
                writer.close();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("合同输出流关闭失败", e);
            }
        }
    }
}
