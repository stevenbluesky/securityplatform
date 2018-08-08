package cn.com.isurpass.house.util;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import java.io.*;

public class PhoneCardInterfaceCallUtils {
    /**
     * 根据手机卡序列号跟新手机卡状态
     * @param iccid
     * @param status
     * @return
     * @throws Exception
     */
    public static String updateStatus(Object iccid,String status) throws Exception{
        //curl -X GET --header "Accept: application/json" --header "Authorization: Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=" "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1"
        String urlStr = "https://restapi7.jasper.com/rws/api/v1/devices/"+String.valueOf(iccid);
        String params = "{\"status\":\""+status+"\"}";
        byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流
        HttpClient httpClient = new HttpClient();// 客户端实例化
        //REST风格，PUT请求表示修改
        PutMethod putMethod = new PutMethod(urlStr);
        //设置请求头Authorization 认证信息，用户名和API Key经过Base64加密后得到
        putMethod.setRequestHeader("Authorization",Constants.Authorization);
        // 设置请求头  Content-Type
        putMethod.setRequestHeader("Content-Type", "application/json");
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
        InputStreamRequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length, "application/json; charset=utf-8"); // 请求体
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
        JSONObject jsStr = JSONObject.parseObject(result);
        String serialnumber = (String)jsStr.get("iccid");
        String errorMessage = (String)jsStr.get("errorMessage");
        String errorCode = (String)jsStr.get("errorCode");
        if(errorCode!=null||errorMessage!=null){
            throw new MyArgumentNullException("errorMessage:"+errorMessage+";errorCode:"+errorCode);
        }
        return jsStr.toString();
    }

    /**
     * 获取手机卡状态
     * @throws Exception
     */
    public static String interfaceCallGet(Object iccid) throws Exception{
        //curl -X GET --header "Accept: application/json" --header "Authorization: Basic YW1ldGFjYTphNDRmNDJmMS0wMmRiLTRhY2EtYjZhMC04MWYxNTBmZGI1YmI=" "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1"
        String gg = "https://restapi7.jasper.com/rws/api/v1/devices?modifiedSince=2016-04-18T17%3A31%3A34%2B00%3A00&pageSize=50&pageNumber=1";
        String urlStr = "https://restapi7.jasper.com/rws/api/v1/devices/"+String.valueOf(iccid);
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
        httpClient.executeMethod(getMethod);// 执行请求  有时会出错java.net.UnknownHostException: restapi7.jasper.com
        InputStream soapResponseStream = getMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
         try {
             datas = readInputStream(soapResponseStream);// 从输入流中读取数据
         } catch (Exception e) {
             e.printStackTrace();
         }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        // 打印返回结果
        JSONObject jsStr = JSONObject.parseObject(result);
        String status = (String)jsStr.get("status");
        String errorMessage = (String)jsStr.get("errorMessage");
        String errorCode = (String)jsStr.get("errorCode");
        if(errorCode!=null||errorMessage!=null){
            throw new MyArgumentNullException("errorMessage:"+errorMessage+";errorCode:"+errorCode);
        }
        return result;
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
