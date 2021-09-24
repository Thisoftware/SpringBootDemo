package com.demo.boot.core.controller;

import com.demo.boot.api.annotation.PassVerify;
import com.demo.boot.api.annotation.Timer;
import com.demo.boot.api.constants.ReData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wyl on 2017-06-13.
 */
@Controller
public class TestController {

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
