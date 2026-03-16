package com.lyj.job1000.controller;

import com.lyj.job1000.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {
    @Autowired
    private RestTemplate restTemplate;

    //模块一的地址
    private final String firstUrl="http://localhost:8080/user/login";

    @RequestMapping("/proxy/login")
    public String proxyLogin(String username,String password){
        LinkedMultiValueMap<String,String> post=new LinkedMultiValueMap<>();
        post.add("username",username);
        post.add("password",password);
        String response=restTemplate.postForObject(firstUrl,post,String.class);
        return "代理返回:"+response;
    }
}
