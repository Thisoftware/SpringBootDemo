package com.demo.boot.core.controller;

import com.demo.boot.api.annotation.PassVerify;
import com.demo.boot.api.constants.ReData;
import com.demo.boot.api.exception.ApiCommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author kis
 * @since 2023/05/24 11:24:59
 */
@RestController
@RequestMapping(value = "api/file", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"handle file"})
@Slf4j
public class HandleFileController {

    @ApiOperation(value = "downloadExcel", notes = "downloadExcel")
    @PostMapping(value = "downloadExcel")
    @PassVerify
    public ReData<String> downloadExcel(HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String fileName = "reportTemplate.xlsx";
            inputStream = this.getClass().getClassLoader().getResourceAsStream("excelTemplate/" + fileName);
//            String path = new File("start/src/main/resources/excelTemplate/" + fileName).getCanonicalPath();  get other module fail
//            inputStream = Files.newInputStream(Paths.get(path));
            outputStream = response.getOutputStream();
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            outputStream.write(bytes);
            outputStream.flush();
        }catch (Exception e) {
            log.error("download error", e);
            throw new ApiCommonException(e);
        }finally {
            try {
                if(outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }catch (Exception e) {
                log.error("close io error", e);
            }
        }
        return ReData.success();
    }
}
