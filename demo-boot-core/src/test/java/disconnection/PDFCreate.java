package disconnection;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Address;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PDFCreate {

    public static String getCurrentDate() {
        Date date = new Date();
        //创建格式化日期时间对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String prepareContract() {
        FileOutputStream fileOutputStream = null;
        ByteArrayOutputStream outputStream = null;
        String newContractLink = null;
        try {
            String contractTemp = "installments.ftl";
//            String contractFileName = UUID.randomUUID() + "contract.pdf";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("contract_number", 123);
            paramMap.put("payment_success_time", getCurrentDate());
            paramMap.put("user_name", "loan");
            paramMap.put("sign_name", "");
            paramMap.put("user_address", "HeNan");
            paramMap.put("user_ktp", 456);
            paramMap.put("fund_user_name", "test name");
            paramMap.put("fund_user_address", "LuoYang");
            paramMap.put("fund_user_nationality", "HanZu");
            paramMap.put("user_email", "123@qq.com");


            //分期产品方式 10-普通分期产品 20-月分期产品
            paramMap.put("loan_days", "20");
            paramMap.put("day_rate", "0.25" + "%");
            paramMap.put("order_principal", 123);
            paramMap.put("total_interest_fee", 456);
            paramMap.put("total_repayment_amount", 456);
            paramMap.put("total_management_fee", 123);
            paramMap.put("insurance_fee_all", 456);
            paramMap.put("repayment_date", getCurrentDate());
            paramMap.put("image_url", "http://media-test-dokuin-id.oss-accelerate.aliyuncs.com/contractsignImageFile/2023-01-03/128991895279863073sign.jpg");

            outputStream = PdfGenerator.exportPdf(paramMap, contractTemp);
            fileOutputStream = new FileOutputStream("D:\\app\\test12.pdf");
            fileOutputStream.write(outputStream.toByteArray());

//            String filePath = ossHelper.upload(contractFileName, inputStream);
//            newContractLink = ossHelper.getOssUrl(filePath);
        } catch (Exception e) {
            log.error("生成/上传合同失败:", e);
            throw new RuntimeException("生成/上传合同失败:" + e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("生成流关闭输出流失败 orderId:{}", "123");
                }
            }
        }
        return newContractLink;
    }

    @Test
    public void testMakePdf() {
        prepareContract();
    }

}
