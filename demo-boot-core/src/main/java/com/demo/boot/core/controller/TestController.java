package com.demo.boot.core.controller;

import com.demo.boot.api.annotation.PassVerify;
import com.demo.boot.api.annotation.Timer;
import com.demo.boot.api.constants.ReData;
import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.core.util.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by wyl on 2017-06-13.
 */
@Controller
@Slf4j
public class TestController {

    @RequestMapping("verifyCode")
    @ResponseBody
    @PassVerify
    public ReData<String> getVerifyCode(){
        try (ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream()) {
            /*获取验证码*/
            String code = CaptchaUtil.generateVerifyCode(4);
            /*验证码已key，value的形式缓存到redis 存放时间一分钟*/
            log.info("验证码============: {}", code);
            String uuid = UUID.randomUUID().toString();
            //RedisUtil.set(RedisKeyConstants.CAPTCHA_KEY + uuid, 180, code);

            CaptchaUtil.outputImage(135, 40, jpegOutputStream, code);
            String base64 = Base64.getEncoder().encodeToString(jpegOutputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");

            ReData<String> reData = new ReData<>();
            reData.setData("验证码base64：" + captchaBase64 + "，验证码key：" + uuid);
            return reData;
        } catch (Exception e) {
            log.error("获取验证码异常", e);
            throw new ApiCommonException(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY);
        }
    }

    @RequestMapping("login")
    @PassVerify
    public String login(Model model){
        model.asMap();
        return "login";
    }
    @RequestMapping("index")
    @PassVerify
    public String index(){
        return "index";
    }

    @RequestMapping("hello")
    @ResponseBody
    @Timer
    @PassVerify
    public ReData<String> getMessage(String s){
        ReData<String> reData = new ReData<>();
        reData.setData(s);
        return reData;
    }

}
