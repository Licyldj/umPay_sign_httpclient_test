package com.umpay_sign_httpclient.httpclientDemo;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class APIHttpClient {
    private String apiURL="";
    private Log logger = LogFactory.getLog(this.getClass());
    private static final String Pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private HttpClient httpClient = null;
    private HttpPost method = null;
    private long startTime = 0L;
    private long endTime = 0L;
    private int status = 0;;
    
    /**
     * 接口地址
     * @param url
     */
    public APIHttpClient(String url){
        if(url != null){
            this.apiURL = url;
        }
        if(apiURL != null){
            httpClient = new DefaultHttpClient();
            method = new HttpPost(apiURL);
        }
    }
    
    public String post(String parameters){
        String body =null;
        logger.info("parameters:"+parameters);
        if(method!=null & parameters!= null & !"".equals(parameters.trim())){
            JSONArray jsonObject = JSONArray.fromObject(parameters);
            logger.info("json:"+jsonObject.toString());
            try {
            List<NameValuePair> params = new  ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("data", parameters));
            method.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
            startTime = System.currentTimeMillis();
            HttpResponse response = httpClient.execute(method);
            endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("statusCode:"+statusCode);
            logger.info("调用API花费了："+(endTime-startTime));
            if(statusCode != HttpStatus.SC_OK){
                logger.error("Method failed:"+response.getStatusLine());
                status=1;
            }
            body = EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                logger.error("exception occurred!/n"+ExceptionUtils.getFullStackTrace(e));
            }finally{
                logger.info("调用接口状态："+status);
            }
        }
        return body;
                
    }
    
    /**
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
     * @return the status
     */
    public int getStatus() {
        return status;
    }
 
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }
    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }
    
    public static void main(String[] args) {
        APIHttpClient testHttpClient = new APIHttpClient("http://www.baidu.com/s?q=httpClient");
        String end = testHttpClient.post("[{json:abc},{json:dfc}]");
        System.out.println(end);
    }
}
