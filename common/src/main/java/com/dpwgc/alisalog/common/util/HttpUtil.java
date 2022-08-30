package com.dpwgc.alisalog.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP客户端工具
 */
@Component
public class HttpUtil {
    /**
     * HTTP GET请求
     */
    public String doGet(String url) {

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //默认值GET
            con.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            return response.toString();

        } catch (Exception e) {
            LogUtil.error("HttpUtil doGet error",e.getMessage());
            return "-1";
        }
    }

    /**
     * HTTP POST请求
     */
    public static String doPost(String url, String JSONBody) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(JSONBody));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity, "UTF-8");

            response.close();
            httpClient.close();

            return responseContent;

        } catch (Exception e) {
            LogUtil.error("HttpUtil doPost error",e.getMessage());
            return "-1";
        }
    }
}
