package com.isurpass.house;

import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.PhoneCardInterfaceCallUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class InterfaceCall {
    @Test
    public void ttest() throws Exception {
        PhoneCardInterfaceCallUtils.updateStatus("89302690201003397968", Constants.ACTIVATED);
    }

    @Test
    public void interfaceCallPut() throws Exception{
        //curl -X GET --header "Accept: application/json" --header "Authorization: Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=" "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1"
        String iccid = "89302690201003397968";
        String status = "ACTIVATED";
        String urlStr = "https://restapi7.jasper.com/rws/api/v1/devices/"+iccid;
        String params = "{\"status\":\""+status+"\"}";
        byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流
        HttpClient httpClient = new HttpClient();// 客户端实例化
        PutMethod putMethod = new PutMethod(urlStr);
        //设置请求头Authorization
        putMethod.setRequestHeader("Authorization", "Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=");
        // 设置请求头  Content-Type
        putMethod.setRequestHeader("Content-Type", "application/json");
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length, "application/json; charset=utf-8"); // 请求体
        putMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(putMethod);// 执行请求
        InputStream soapResponseStream = putMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// 从输入流中读取数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        // 打印返回结果
        System.out.println(result);
    }
    @Test
    public void interfaceCallGet() throws Exception{
        //curl -X GET --header "Accept: application/json" --header "Authorization: Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=" "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1"
        String gg = "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1";
        String urlStr = "https://restapi7.jasper.com/rws/api/v1/devices/89302690201003397976";
        String params = "1";
        byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流
        HttpClient httpClient = new HttpClient();// 客户端实例化
        GetMethod getMethod = new GetMethod(urlStr);
        //设置请求头Authorization
        getMethod.setRequestHeader("Authorization", "Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=");
         // 设置请求头  Content-Type
        getMethod.setRequestHeader("Content-Type", "application/json");
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length, "application/json; charset=utf-8"); // 请求体
        //getMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(getMethod);// 执行请求
        InputStream soapResponseStream = getMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
         try {
             datas = readInputStream(soapResponseStream);// 从输入流中读取数据
         } catch (Exception e) {
             e.printStackTrace();
         }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        // 打印返回结果

        System.out.println(result);
    }

    /**
     * 从输入流中读取数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
   public static byte[] readInputStream(InputStream inStream) throws Exception {
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       byte[] buffer = new byte[1024];
       int len = 0;
       while ((len = inStream.read(buffer)) != -1) {
           outStream.write(buffer, 0, len);
       }
       byte[] data = outStream.toByteArray();
       outStream.close();
       inStream.close();
       return data;
   }
}
