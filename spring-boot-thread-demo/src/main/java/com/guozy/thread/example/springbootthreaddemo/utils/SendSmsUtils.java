package com.guozy.thread.example.springbootthreaddemo.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.HashMap;
import java.util.Map;

public class SendSmsUtils {

    private static final String URL = "http://sms.webchinese.cn/web_api/";

    public static Map<String, Integer> sendMessage(String phone) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(URL);
        client.getParams().setContentCharset("GBK");
        post.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

        String content = "您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。";

        NameValuePair[] data = {//提交短信
                new NameValuePair("Uid", "guozy"),//sms网注册的用户名
                new NameValuePair("key", "d41d8cd98f00b204e980"), //sms密钥
                new NameValuePair("smsMob", phone),
                new NameValuePair("smsText", content),
        };
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);//返回值信息
        post.releaseConnection();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("result", Integer.parseInt(result));
        map.put("mobile_code", mobile_code);
        System.out.println("map:" + map);
        return map;
    }
}
