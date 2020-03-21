package com.wxj.spring.springbootdemo.provider;

import com.alibaba.fastjson.JSON;
import com.wxj.spring.springbootdemo.dto.AccessTokenDTO;
import com.wxj.spring.springbootdemo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    //参数超过两个把它封装成对象
    public String  getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(com.alibaba.fastjson.JSON.toJSONString(accessTokenDTO), JSON);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String accessToken = response.body().string();
            accessToken = accessToken.split("&")[0].split("=")[1];
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public GithubUser getUserInfor(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//string解析成java类对象
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
