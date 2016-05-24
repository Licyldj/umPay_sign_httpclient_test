package com.umpay_sign_httpclient.umPaySignDemo;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.umpay.api.common.ReqData;
import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.umpay.api.paygate.v40.Plat2Mer_v40;

public class TransferQueryDemo {
    public static void main(String[] args) throws Exception {
        String url = getUrl();
        ///创建HttpClientBuilder 
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //创建httpclient
        CloseableHttpClient httpClient = httpClientBuilder.build();
        //创建请求对象
        HttpGet httpGet = new HttpGet(url);
        //设置头部参数信息
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");   
//        
//        httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
//        
//        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");  
//  
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");  
//  
//        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");  
//  
//        httpGet.setHeader("Connection", "keep-alive");  
        
        System.out.println(httpGet.getURI());
      //执行get请求  
        HttpResponse httpResponse = httpClient.execute(httpGet);  
        //获取响应消息实体  
        HttpEntity entity = httpResponse.getEntity();  
        //响应状态  
        System.out.println("status:" + httpResponse.getStatusLine());  
        //判断响应实体是否为空  
        if (entity != null) {  
            System.out.println("contentEncoding:" + entity.getContentEncoding());  
            String respStr = EntityUtils.toString(entity);
            System.out.println("response content:" + respStr);  
            Map resMap = Plat2Mer_v40.getResData(respStr);
            System.out.println("resMap:" + resMap.toString());
            System.out.println(resMap.size());
        }  
    }
    

    public static String getUrl() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("service","transfer_query");
        map.put("charset","UTF-8");
        map.put("mer_id","3486");
        map.put("res_format","HTML");
        map.put("version","4.0");
        map.put("sign_type","RSA");
        map.put("order_id","20160524254398138");
        map.put("mer_date","20160524");


        ReqData reqDatePost = Mer2Plat_v40.makeReqDataByPost(map);
        String reqDatePost_url = reqDatePost.getUrl();
        ReqData reqDateGet = Mer2Plat_v40.makeReqDataByGet(map);
        String reqDateGet_url = reqDateGet.getUrl();
        return reqDateGet_url;
    }

}
