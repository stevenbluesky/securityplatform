package cn.com.isurpass.house.request;

import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.TokenKeeper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.logging.Logger;

public class HttpsUtils {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * httpClient post请求
     *
     * @param url    请求url
     * @param header 头部信息
     * @param param  请求参数 form提交适用
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     */
    public static String post(String url, Map<String, String> header, Map<String, String> param, HttpEntity entity){
        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // 设置头信息
            if (MapUtils.isNotEmpty(header)) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置请求参数
            if (MapUtils.isNotEmpty(param)) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    //给参数赋值
                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 设置实体 优先级高
            if (entity != null) {
                httpPost.setEntity(entity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                readHttpResponse(httpResponse);
            }
        } catch (Exception e) {
//            throw e;
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        return httpClient;
    }

    public static String readHttpResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        builder.append("status:" + httpResponse.getStatusLine());
        builder.append("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            builder.append("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append("response length:" + responseString.length());
            builder.append("response content:" + responseString.replace("\r\n", ""));
        }
        return builder.toString();
    }

    public static String toggleDevice(Integer zwavedeviceid,Integer channel,String url){
//        String url = "https://app.aibasecloud.com/iremote/thirdpart/zufang/closedevice";
        Map<String, String> map = new HashMap<>();
        map.put("token", TokenKeeper.getToken());
        map.put("zwavedeviceid", zwavedeviceid.toString());
        map.put("channel", channel.toString());
        String post = HttpsUtils.post(url, null, map, null);
        JSONObject jo = JSONObject.parseObject(post);
        if (jo == null || jo.getInteger("resultCode") == 30300) {
            TokenKeeper.getNewToken();
            map.put("token", TokenKeeper.getToken());
            map.put("zwavedeviceid", zwavedeviceid.toString());
            map.put("channel", channel.toString());
            System.out.println(TokenKeeper.getToken());
            return HttpsUtils.post(url, null, map, null);
        }
//        System.out.println(TokenKeeper.getToken());
        return post;
    }
    public static String openDevice(Integer zwavedeviceid){
        String url = "https://"+ Constants.OPERATE_DEVICE_URL+"/iremote/thirdpart/zufang/opendevice";
        return toggleDevice(zwavedeviceid, 1, url);
    }
    public static String closeDevice(Integer zwavedeviceid){
        String url = "https://"+ Constants.OPERATE_DEVICE_URL+"/iremote/thirdpart/zufang/closedevice";
        return toggleDevice(zwavedeviceid, 1, url);
    }
    public static String getQrInfo(String deviceid){
        String url = "https://"+ Constants.OPERATE_DEVICE_URL+"/iremote/thirdpart/zufang/querygatewayqrcode";
        Map<String, String> map = new HashMap<>();
        map.put("token", TokenKeeper.getToken());
        map.put("deviceid", deviceid);
        String post = HttpsUtils.post(url, null, map, null);
        JSONObject jo = JSONObject.parseObject(post);
        if (jo == null || jo.getInteger("resultCode") == 30300) {
            TokenKeeper.getNewToken();
            map.put("token", TokenKeeper.getToken());
            map.put("deviceid", deviceid);
            return HttpsUtils.post(url, null, map, null);
        }
        return post;
    }
    public static String SetAdvertBanner(String phonenumber,Integer advertbannerid){
        String url = "https://"+ Constants.OPERATE_DEVICE_URL+"/iremote/thirdpart/user/setadvertbanner";
        Map<String, String> map = new HashMap<>();
        map.put("token", TokenKeeper.getToken());
        map.put("phonenumber", phonenumber);
        map.put("advertbannerid", String.valueOf(advertbannerid));
        String post = HttpsUtils.post(url, null, map, null);
        JSONObject jo = JSONObject.parseObject(post);
        if (jo == null || jo.getInteger("resultCode") == 30300) {
            TokenKeeper.getNewToken();
            map.put("token", TokenKeeper.getToken());
            map.put("phonenumber", phonenumber);
            map.put("advertbannerid", String.valueOf(advertbannerid));
            return HttpsUtils.post(url, null, map, null);
        }
        return post;
    }
    public static void main(String[] args) throws Exception {
      /*  for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("loginname", "00"+(50001+i));
//        map.put("code", "thirdparter_open_demo");
//        map.put("password", "c1412bf534b84da289662efba5f10761989276");
            map.put("token", "263e881c34c04e04b3db2a8226cf11cf364894");
//            map.put("loginname","");
//        map.put("zwavedeviceid", "11544");
//        map.put("channel ", "[0,255]");
            String post = HttpsUtils.post("https://test.isurpass.com.cn/iremote/thirdpart/zufang/querythirdpartdevices", null, map, null);
            System.out.println(post);
            Thread.sleep(50);
        }*/

//      String url = "https://app.aibasecloud.com/iremote/thirdpart/login";
//      url = "https://app.aibasecloud.com/iremote/thirdpart/zufang/closedevice";
//        Map<String, String> map = new HashMap<>();
////        map.put("code", "tp_jwzh_ameta_all");
////        map.put("password", "H8yAETIfB50f1rEElR5yZ68B");
//        map.put("token", "a5ed58fe42184cc0a015c4368fe50b78340097");
//        map.put("zwavedeviceid", "20");
//        map.put("channel", "1");
////        String post = HttpsUtils.post("https://app.aibasecloud.com/iremote/thirdpart/login", null, map, null);
//        String post = HttpsUtils.post(url, null, map, null);
//        JSONObject jo = JSONObject.parseObject(post);
//
//        System.out.println(jo.getString("resultCode"));
        String s = openDevice(11542);
        System.out.println(s);
    }
}