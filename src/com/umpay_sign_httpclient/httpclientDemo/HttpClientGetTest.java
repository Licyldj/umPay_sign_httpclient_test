package com.umpay_sign_httpclient.httpclientDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGetTest {
    private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception, IOException {
        String url = "https://www.baidu.com/con?from=self?_t=1464052756486";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        System.out.println(response.toString());
        
//        System.out.println(response.getEntity());
//        
//        System.out.println(EntityUtils.toString(response.getEntity()));
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);
    }
}
