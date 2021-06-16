package com.demo.boot.core;

import com.demo.boot.api.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wyl on 2017-06-.13
 */
@SpringBootApplication()
@Slf4j
public class Application {

    public static void main(String[] args) {
        System.getProperties().setProperty("file.encoding", "UTF-8");
        System.getProperties().setProperty("user.language", "zh");
        System.getProperties().setProperty("user.country", "CN");
        System.getProperties().setProperty("sun.jnu.encoding", "UTF-8");
        Environment env = startApplication(args).getEnvironment();
        log.info(getApplicationInfo(env));
    }

    private static ConfigurableApplicationContext startApplication(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ApplicationPidFileWriter());
        return app.run(args);
    }

    private static String getApplicationInfo(Environment env) {
        String protocol = env.getProperty("server.ssl.key-store") == null ? "http" : "https";
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostAddress = "Unknown host address";
        }
        return "\n----------------------------------------------------------\n\t" + "Application Service '"
                + env.getProperty("spring.application.name") + "' is running! Access URLs:\n\t" + "Local: \t\t"
                + protocol + "://localhost:" + env.getProperty("server.port")
                + StringUtil.nullToEmpty(env.getProperty("server.contextPath")) + "\n\t" + "External: \t" + protocol
                + "://" + hostAddress + ":" + env.getProperty("server.port")
                + StringUtil.nullToEmpty(env.getProperty("server.contextPath")) + "\n\t" + "Profile(s): \t["
                + String.join(",", env.getActiveProfiles())
                + "]\n----------------------------------------------------------";
    }
}



