package com.umpay_sign_httpclient.umPaySignDemo;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.umpay.api.common.ReqData;
import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.umpay.api.paygate.v40.Plat2Mer_v40;

public class TransferDirectDemo {

    public static void main(String[] args) throws Exception {

        String url = getUrl();
        // 1.创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2.创建httpget httppost
        HttpPost httpPost = new HttpPost(url);
        System.out.println("executing request " + httpPost.getURI());
        HttpGet httpGet = new HttpGet(url);
        System.out.println("executing request " + httpGet.getURI());
        // 3.创建参数列表
        // List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // formparams.add(new BasicNameValuePair("", ""));
        
        
        
        // 4.执行get请求.
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 5.获取响应实体
        System.out.println("相应实体状态：" + response.getStatusLine());
        HttpEntity entity = response.getEntity();
        System.out.println("----------------------------");
        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());
            // 打印响应内容
            String resString = EntityUtils.toString(entity);
            System.out.println("Response content: " + resString);
            Map<String, Object> resMap = Plat2Mer_v40.getResData(resString);
            System.out.println("resMap:" + resMap.toString());
            System.out.println(resMap.size());
        }

        System.out.println("----------------------------");
        //关闭流并释放资源
        response.close();
        httpClient.close();
    }

    public static String getUrl() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("service","transfer_direct_req");
        map.put("charset","UTF-8");
        map.put("mer_id","3486");
        map.put("sign_type","RSA");
        map.put("notify_url","http://www.baidu.com");
        map.put("res_format","HTML");
        map.put("version","4.0");
        map.put("order_id","20160524651055565");
        map.put("mer_date","20160524");
        map.put("amount","1");
        map.put("recv_account_type","00");
        map.put("recv_bank_acc_pro","0");
        map.put("recv_account","00");
        map.put("recv_user_name","阿斯蒂芬");
        map.put("identity_type","");
        map.put("identity_code","1234123412341234");
        map.put("identity_holder","阿道夫");
        map.put("media_type","");
        map.put("media_id","333");
        map.put("recv_gate_id","CMB");
        map.put("purpose","付款测试");
        map.put("prov_name","北京");
        map.put("city_name","北京");
        map.put("bank_brhname","XX支行");
        map.put("checkFlag","1");
        map.put("mobile_no","123412341234");


        ReqData reqDatePost = Mer2Plat_v40.makeReqDataByPost(map);
        String reqDatePost_url = reqDatePost.getUrl();
        ReqData reqDateGet = Mer2Plat_v40.makeReqDataByGet(map);
        String reqDateGet_url = reqDateGet.getUrl();
        return reqDateGet_url;
    }

}
