package com.huiju.blackbrin.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 类说明：
 */
public class HttpUtils {

    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static <E> E GET(String URL, List<NameValuePair> params, Class<E> returnClass) throws Exception {
        String fullURL;
        if (null == URL) {
            throw new HttpException("URL参数不能为空！");
        } else if (null == params || params.size() == 0) {
            fullURL = URL;
        } else {
            fullURL = URL + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(fullURL);
        request.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);
        return mappingResponse(response, returnClass);
    }

    public static <E> E POST(String URL, Object params, Class<E> returnClass) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(URL);
        request.setHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json; charset=utf-8");
        request.setEntity(new StringEntity(new Gson().toJson(params), Charset.forName("UTF-8")));
        log.info(new Gson().toJson(params));
        HttpResponse response = client.execute(request);
        return mappingResponse(response, returnClass);
    }

    public static <E> E PUT(String URL, Object params, Class<E> returnClass) throws Exception {
    	CloseableHttpClient client = HttpClients.createDefault();
    	HttpPut request = new HttpPut(URL);
    	request.setHeader("Accept", "application/json");
    	request.addHeader("Content-type", "application/json; charset=utf-8");
    	request.setEntity(new StringEntity(new Gson().toJson(params), Charset.forName("UTF-8")));
    	log.info(new Gson().toJson(params));
    	HttpResponse response = client.execute(request);
    	return mappingResponse(response, returnClass);
    }
    
    private static <E> E mappingResponse(HttpResponse response, Class<E> returnClass) throws Exception {
    	if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            EntityUtils.consumeQuietly(response.getEntity());
            throw new HttpException(response.getStatusLine().getStatusCode() + ":" + response.getStatusLine().getReasonPhrase());
        } 
        
        if(returnClass == null){
        	return (E) response;
        }
        else {
            String jsonBody = EntityUtils.toString(response.getEntity());
            log.info(jsonBody);
            EntityUtils.consumeQuietly(response.getEntity());
            Gson gson = new Gson();
            return gson.fromJson(jsonBody, returnClass);
        }
    }
}
