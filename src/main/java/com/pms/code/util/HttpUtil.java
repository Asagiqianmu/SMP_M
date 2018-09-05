package com.pms.code.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.taobao.api.internal.toplink.logging.LogUtil;

import net.sf.json.JSONObject;

public class HttpUtil {

	// User-Agent
	public static final String USERAGENT_FIREFOX = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0";
	public static final String USERAGENT_IE = "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko";

	private BasicCookieStore cookieStore;
	private HttpPost post;

	public static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output) throws IOException {
		URL url = new URL(requestUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}
		// 从输入流读取返回内容
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	}

	public HttpResult doGet(String url, Map<String, String> headers, Map<String, String> params)
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClientProtocolException, IOException {

		if (url == null || url.equals("")) {
			return null;
		}

		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
		cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();

		HttpResult result = null;
		try {

			url = url + "?" + parseParams(params);
			HttpGet httpget = new HttpGet(url);
			httpget.setHeaders(parseHeader(headers));

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					result = new HttpResult();
					result.setCookies(cookieStore.getCookies());
					result.setStatusCode(response.getStatusLine().getStatusCode());
					result.setHeaders(response.getAllHeaders());
					result.setBody(EntityUtils.toString(entity));
				}

			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result;

	}

	public HttpResult doPost(String url, Map<String, String> postData, String encoding) throws Exception {

		if (url == null || url.equals("")) {
			return null;
		}
		if (encoding == null || encoding.equals("")) {
			encoding = "utf-8";
		}
		BasicCookieStore cookieStore = new BasicCookieStore();
		SSLContextBuilder builder = new SSLContextBuilder();

		builder.loadTrustMaterial(new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		});

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new String[] { "TLSv1.2" }, null, new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				hostname = "112.74.196.157";
				return SSLConnectionSocketFactory.getDefaultHostnameVerifier().verify(hostname, session);
			}
		});

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(null).setSSLSocketFactory(sslsf).build();

		post = new HttpPost(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : postData.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encoding);
		entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(new UrlEncodedFormEntity(list, encoding));

		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity httpentity = response.getEntity();

		System.out.println("----------" + response.getStatusLine().getStatusCode());
		HttpResult result = new HttpResult();
		result.setCookies(cookieStore.getCookies());
		result.setStatusCode(response.getStatusLine().getStatusCode());
		result.setHeaders(response.getAllHeaders());
		result.setBody(EntityUtils.toString(httpentity, encoding));

		close(entity, response);

		return result;
	}

	private String parseParams(Map<String, String> params) {
		if (params == null || params.isEmpty()) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (String key : params.keySet()) {
			sb.append(key + "=" + params.get(key) + "&");
		}
		return sb.substring(0, sb.length() - 1);

	}

	private Header[] parseHeader(Map<String, String> headers) {
		if (headers == null || headers.isEmpty()) {
			return getDefaultHeaders();
		}

		Header[] retHeader = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			retHeader[i++] = new BasicHeader(str, headers.get(str));
		}
		return retHeader;
	}

	private Header[] getDefaultHeaders() {
		Header[] headers = new BasicHeader[3];
		headers[0] = new BasicHeader("User-Agent", USERAGENT_IE);
		headers[1] = new BasicHeader("Accept-Encoding", "gzip, deflate");
		headers[2] = new BasicHeader("Accept-Language", "en-US,en;q=0.8,zh-Hans-CN;q=0.5,zh-Hans;q=0.3");
		return headers;
	}

	private void close(HttpEntity entity, CloseableHttpResponse response) {
		try {
			if (entity != null) {
				InputStream input = entity.getContent();
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            下载文件的链接
	 * @param destFile
	 *            包含路径的目标文件名
	 * @param headers
	 *            请求头
	 * @return
	 */
	public HttpResult downloadFile(String url, String destFile, Map<String, String> headers) throws Exception {

		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();

		HttpGet get = new HttpGet(url);
		get.setHeaders(parseHeader(headers));
		InputStream input = null;
		CloseableHttpResponse response = null;
		HttpResult result = null;

		try {
			response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			input = entity.getContent();
			File file = new File(destFile);

			FileOutputStream fos = new FileOutputStream(file);
			int len = -1;
			byte[] tmp = new byte[1024];
			while ((len = input.read(tmp)) != -1) {
				fos.write(tmp, 0, len);
			}
			fos.flush();
			fos.close();

			result = new HttpResult();
			result.setCookies(cookieStore.getCookies());
			result.setStatusCode(response.getStatusLine().getStatusCode());
			result.setHeaders(response.getAllHeaders());
			result.setBody(EntityUtils.toString(entity, Consts.UTF_8));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("--------"+sendPost("https://www.d-power.com.cn:13010/open", "{\"door\":\"25005972\",\"tel\":\"17378200351\"}"));
	}

	public static boolean sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}
	

	public static JSONObject sendPostReq(String url, String jsonParam) {
		JSONObject json = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置文件类型:
            conn.setRequestProperty("contentType", "application/x-www-form-urlencoded");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(jsonParam);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			if(!result.equals("")){
				json = JSONObject.fromObject(result);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	/** 
     * post请求(用于key-value格式的参数) 
     * @param url 
     * @param params 
     * @return 
     */  
    public static JSONObject doPost(String url, Map params){  
          
        BufferedReader in = null;    
        try {    
            // 定义HttpClient    
            HttpClient client = new DefaultHttpClient();    
            // 实例化HTTP方法    
            HttpPost request = new HttpPost();    
            request.setURI(new URI(url));  
              
            //设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {  
                String name = (String) iter.next();  
                String value = String.valueOf(params.get(name));  
                nvps.add(new BasicNameValuePair(name, value));  
                  
                //System.out.println(name +"-"+value);  
            }  
            request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));  
              
            HttpResponse response = client.execute(request);    
            int code = response.getStatusLine().getStatusCode();  
            if(code == 200){    //请求成功  
                in = new BufferedReader(new InputStreamReader(response.getEntity()    
                        .getContent(),"utf-8"));  
                StringBuffer sb = new StringBuffer("");    
                String line = "";    
                String NL = System.getProperty("line.separator");    
                while ((line = in.readLine()) != null) {    
                    sb.append(line + NL);    
                }  
                  
                in.close();    
                return JSONObject.fromObject(sb.toString());  
            }  
            else{   //  
                System.out.println("状态码：" + code);  
                return null;  
            }  
        }  
        catch(Exception e){  
            e.printStackTrace();  
              
            return null;  
        }  
    }  
}
