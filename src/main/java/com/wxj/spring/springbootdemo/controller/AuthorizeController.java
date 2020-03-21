package com.wxj.spring.springbootdemo.controller;

import com.wxj.spring.springbootdemo.dto.AccessTokenDTO;
import com.wxj.spring.springbootdemo.dto.GithubUser;
import com.wxj.spring.springbootdemo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.PrimitiveIterator;
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private AccessTokenDTO accessTokenDTO;

    @Value("${github.redirect.url}")
    private String redirectUri;
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser userInfor = githubProvider.getUserInfor(accessToken);
        System.out.println(userInfor.getBio());
        return "index";
    }
}
