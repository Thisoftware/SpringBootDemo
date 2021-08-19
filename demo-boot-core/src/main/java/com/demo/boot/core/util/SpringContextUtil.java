package com.demo.boot.core.util;

import com.demo.boot.core.client.MethodExecClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public Object getBean(String className, Class<MethodExecClient> tClass){
        return applicationContext.getBean(className,tClass);
    }
}
