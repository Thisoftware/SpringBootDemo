package com.xxx.controller;

import com.xxx.common.consts.ReCode;
import com.xxx.common.consts.ReData;
import com.xxx.common.util.ReUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangyaoliang on 2017-06-13.
 */
@Controller
public class TestController {

    @RequestMapping("login")
    public String index(Model model){
        model.asMap();
        return "login";
    }

    @RequestMapping("hello")
    @ResponseBody
    public ReData getMessage(String s){
        if(s == null){
            return ReUtil.fail(ReCode.Fail);
        }
        return ReUtil.success(s);
    }

}
