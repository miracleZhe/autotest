package com.youceedu.interf.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author wangzhe
 * @version V1.0
 * @ClassName HttpSendReq
 * @Description Todo
 * @Date 2021/4/13 23:24
 */
public class HttpSendReq {
    public static CookieStore cookieStore=new BasicCookieStore();
    //cookie全局数据区


    /*
     *@author wangzhe
     *@Description 配置请求Header
     *@Date 23:29 2021/4/12
     *@Param [httpRequestBase, param]
     *@Return void
     **/
    public static  void httpRequestConfig(HttpRequestBase httpRequestBase, String param){
        //配置请求Header信息
        httpRequestBase.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0");
        if(JsonUtil.isJsonString(param)||JsonUtil.isJsonArray(param)){
            httpRequestBase.setHeader("Content-Type","application/json; charset=UTF-8");

        }else {
            httpRequestBase.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        }

        //配置超时
        RequestConfig.Builder builder=RequestConfig.custom();
        builder.setConnectionRequestTimeout(4000);
        RequestConfig requestConfig=builder.build();
        httpRequestBase.setConfig(requestConfig);
    }


    /*
     *@author wangzhe
     *@Description 发送get请求
     *@Date 23:07 2021/4/11
     *@Param [url, parameter]
     *@Return java.lang.String
     **/
    public static String sendGet(String url,String parameter) throws  Exception{
        String result=null;
        String finalUrl=url+"?"+parameter;
        CloseableHttpClient httpclient=null;
        CloseableHttpResponse response=null;

        try{

            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            httpClientBuilder.setDefaultCookieStore(cookieStore);
            httpclient=httpClientBuilder.build();
            //通过execute()发送请求,配置请求Header
            HttpGet httpGet = new HttpGet(finalUrl);
            httpRequestConfig(httpGet,parameter);


            //得到服务器的返回值
            response=httpclient.execute(httpGet);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode== HttpStatus.SC_OK){
                // 获取返回值信息
                HttpEntity entity=response.getEntity();

                result= EntityUtils.toString(entity,"utf-8");
            }else{
                result="状态码为："+statusCode+"请查找接口地址和请求参数";
            }

        }finally{
            response.close();
            httpclient.close();
        }
        return result;
    }

    /*
     *@author wangzhe
     *@Description 发送post请求
     *@Date 23:10 2021/4/11
     *@Param [url, parameter]
     *@Return java.lang.String
     **/
    public static  String sendPost(String url,String parameter) throws Exception{
        String result=null;
        CloseableHttpClient httpclient=null;
        CloseableHttpResponse response=null;

        try{
            HttpClientBuilder httpClientBuilder=HttpClients.custom();
            httpClientBuilder.setDefaultCookieStore(cookieStore);
            httpclient=httpClientBuilder.build();
            //通过execute()发送请求
            HttpPost httpPost = new HttpPost(url);
            httpRequestConfig(httpPost,parameter);


            //绑定post请求信息给httpPost对象,配置请求Header
            httpPost.setEntity(new StringEntity(parameter,"utf-8"));


            //发送post请求
            response=httpclient.execute(httpPost);
            int statusCode=response.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.SC_OK){
                // 获取返回值信息
                HttpEntity entity=response.getEntity();

                result=EntityUtils.toString(entity,"utf-8");
            }else{
                result="状态码为："+statusCode+"请查找接口地址和请求参数";
            }

        }finally{
            response.close();
            httpclient.close();
        }
        return result;
    }
    public static void main(String[] args) throws  Exception{
        String result=sendPost("https://reg.lenovovip.com.cn/auth/v2/doLogin","account=13439481286&password=enNudDEyMw%3D%3D&ps=1&ticket=e1176c13-1f8a-480f-a751-3f874df82249&codeid=&code=");
        System.out.println(result);
    }
}