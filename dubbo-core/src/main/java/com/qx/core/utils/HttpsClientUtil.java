package com.qx.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;


public class HttpsClientUtil {

	public static String doPost(String url, Map<String, String> map, String charset) {
		// HttpClient httpClient = null;
		CloseableHttpClient httpclient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			// 创建默认的httpClient实例.
			httpclient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpclient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public void post(String url, Map<String, String> map) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (int i = 0; i < map.size(); i++) {
			formparams.add(new BasicNameValuePair("type", "house"));
		}

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final String get(final String url, final Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("");

        if (null != params && !params.isEmpty()) {
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(params.get(key));
                i++;
            }
        }

        CloseableHttpClient httpClient = createSSLClientDefault();

        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(url + sb.toString());
        String result = "";

        try {
            response = httpClient.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
//            Logger.getLogger(HttpsUtil.class.getName()).log(Level.INFO, null, ex);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
//                    Logger.getLogger(HttpsUtil.class.getName()).log(Level.INFO, null, ex);
                }
            }
        }

        return result;
    }


    private static CloseableHttpClient createSSLClientDefault() {

        SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string){
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException ex) {
//            Logger.getLogger(HttpsUtil.class.getName()).log(Level.INFO, null, ex);
        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(HttpsUtil.class.getName()).log(Level.INFO, null, ex);
        } catch (KeyManagementException ex) {
//            Logger.getLogger(HttpsUtil.class.getName()).log(Level.INFO, null, ex);
        }

        return HttpClients.createDefault();
    }
    
//    public static void main(String[] args) {
//		Map<String, Object> data = new HashMap<>();
//		data.put("app_id", CommonDef.APPID);
//		data.put("secret", CommonDef.SECRET);
//		data.put("grant_type", "client_credential");
//		data.put("version", "1.0.0");
//		String result = get("https://idasc.webank.com/api/oauth2/access_token", data);
//		System.out.println("Access Token 获取:"+result);
//		
//		JSONObject tokenJson = JSONObject.parseObject(result);
//		
//		Map<String, Object> data2 = new HashMap<>();
//		data2.put("app_id", CommonDef.APPID);
//		data2.put("access_token", tokenJson.getString("access_token"));
//		data2.put("user_id", "123");
//		data2.put("type", "NONCE");
//		data2.put("version", "1.0.0");
//		result = get("https://idasc.webank.com/api/oauth2/api_ticket", data2);
//		System.out.println("NONCE ticket 获取:"+result);
//		
//		JSONObject ticketJson = JSON.parseObject(result);
//		Map<String, Object> data3 = new HashMap<>();
//		data3.put("appId", CommonDef.APPID);
//		data3.put("nonceStr", "6c0222ede7f54cada717a9abfb372233");
//		data3.put("userId", "123");
//		data3.put("ticket", ticketJson.getJSONArray("tickets").getJSONObject(0).getString("value"));
//		data3.put("version", "1.0.0");
////		result = get(CommonDef.SIGN_URL, data3);
//		List<String> values = new ArrayList<>();
//		values.add(CommonDef.APPID);
//		values.add("123");
//		values.add("6c0222ede7f54cada717a9abfb372233");
//		values.add("1.0.0");
//		values.add(ticketJson.getJSONArray("tickets").getJSONObject(0).getString("value"));
//		result = sign(values, ticketJson.getJSONArray("tickets").getJSONObject(0).getString("value"));
//		System.out.println(result);
//	}
//    
//    public static String sign(List<String> values, String ticket) {
//        if (values == null) {
//            throw new NullPointerException("values is null");
//        }
//
//        values.removeAll(Collections.singleton(null));// remove null
//        values.add(ticket);
//        java.util.Collections.sort(values);
//
//        StringBuilder sb = new StringBuilder();
//        for (String s : values) {
//            sb.append(s);
//        }
//
//        return Hashing.sha1().hashString(sb, Charsets.UTF_8).toString().toUpperCase();
//    }


}
