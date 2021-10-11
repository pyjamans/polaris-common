package com.knight.polaris.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 通用http请求工具类
 *
 * @author pyjaman
 * @date 2021-06-17
 */
public class GlobalHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHttpUtils.class);

    private static final String HTTP_JSON = "application/json;charset=UTF-8";

    public static String postWithForm(String url, Map<String, Object> paramMap) {
        logger.debug("Http request url => [{}]", url);
        List<BasicNameValuePair> list = new ArrayList<>();
        for(String key : paramMap.keySet()) {
            list.add(new BasicNameValuePair(key, paramMap.get(key).toString()));
        }
        HttpPost request = new HttpPost(url);
        UrlEncodedFormEntity formEntity;
        try {
            formEntity = new UrlEncodedFormEntity(list);
        } catch (UnsupportedEncodingException e) {
            logger.error("Request params exception![参数编码异常!]", e);
            return null;
        }
        request.setEntity(formEntity);
        return request(request);
    }

    public static String postWithForm(String url, String paramStr) {
        Map<String, Object> paramMap = JSON.parseObject(paramStr, Map.class);
        return postWithForm(url, paramMap);
    }

    public static String postWithJson(String url, Map<String, Object> paramMap) {
        String paramStr = JSON.toJSONString(paramMap);
        return postWithJson(url, paramStr);
    }

    public static String postWithJson(String url, String paramStr) {
        logger.debug("Http request url => [{}]", url);
        HttpPost request = new HttpPost(url);
        StringEntity entity;
        try {
            entity = new StringEntity(paramStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("Request params encoding exception![参数编码异常!]", e);
            return null;
        }
        entity.setContentType("text/json");
        request.setHeader("Content-Type", HTTP_JSON);
        request.setEntity(entity);
        return request(request);
    }

    private static String request(HttpUriRequest request) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(request);
            String entity = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            logger.debug("Http response result print => [{}]", entity);
            return entity;
        } catch (Exception e ) {
            logger.error("Http request fail![Http请求失败!]", e);
            return null;
        }
    }

    public static String getWithParams(String url, Map<String, Object> paramMap) {
        StringBuilder builder = new StringBuilder(url);
        if(paramMap != null && !paramMap.isEmpty()) {
            builder.append('?');
            Iterator<String> iterator = paramMap.keySet().iterator();
            while(iterator.hasNext()) {
                String key = iterator.next();
                builder.append(key).append('=').append(paramMap.get(key));
                if(iterator.hasNext()) {
                    builder.append('&');
                }
            }
        }
        return getRequest(builder.toString());
    }

    public static String getRequest(String url) {
        logger.debug("Http request url => [{}]", url);
        HttpGet request = new HttpGet(url);
        return request(request);
    }

    public static String putRequest(String url) {
        logger.debug("Http request url => [{}]", url);
        HttpPut request = new HttpPut(url);
        return request(request);
    }

    public static String deleteRequest(String url) {
        logger.debug("Http request url => [{}]", url);
        HttpDelete request = new HttpDelete(url);
        return request(request);
    }
}
