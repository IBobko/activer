package ru.todo100.activer.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mapping")
public class VkontaktePageController
{
	@ResponseBody
	@RequestMapping("/")
	void index(HttpServletResponse response) throws IOException, NoSuchAlgorithmException, KeyManagementException,
	                                                KeyStoreException
	{
		String url
						= "https://oauth.vk.com/authorize?client_id=5144438&display=page&redirect_uri=http://example" +
				".com/callback&scope=friends&response_type=token&v=5.40";

		//		SSLContext sslContext = SSLContext.getInstance("SSL");
		//
		//
		//		// set up a TrustManager that trusts everything
		//		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
		//			public X509Certificate[] getAcceptedIssuers() {
		//				System.out.println("getAcceptedIssuers =============");
		//				return null;
		//			}
		//
		//			public void checkClientTrusted(X509Certificate[] certs,
		//			                               String authType) {
		//				System.out.println("checkClientTrusted =============");
		//			}
		//
		//			public void checkServerTrusted(X509Certificate[] certs,
		//			                               String authType) {
		//				System.out.println("checkServerTrusted =============");
		//			}
		//		} }, new SecureRandom());
		//
		//		SSLContext sc = SSLContext.getInstance("SSL");
		//		TrustManager[] trustAllCerts;
		//		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		//		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		//
		//		HttpClientBuilder builder = HttpClientBuilder.create();
		//		SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext,
		// SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		//		builder.setSSLSocketFactory(sslConnectionFactory);
		//
		//		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		//				.register("https", sslConnectionFactory)
		//				.build();
		//
		//		HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
		//
		//		builder.setConnectionManager(ccm);
		//
		//		CloseableHttpClient httpclient = builder.build();
		//		HttpGet httpPost = new HttpGet(url);
		//		CloseableHttpResponse res = httpclient.execute(httpPost);
		//
		//		HttpEntity entity = res.getEntity();
		//		response.getWriter().print(EntityUtils.toString(entity));

		//
		//		org.apache.http.client.
		//
		//		SSLContextBuilder builder = new SSLContextBuilder();
		//		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		//
		//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		//				builder.build());
		//		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
		//				sslsf).build();
		//
		//		HttpGet httpGet = new HttpGet(url);
		//		CloseableHttpResponse res = httpclient.execute(httpGet);
		//
		//				HttpEntity entity = res.getEntity();
		//				response.getWriter().print(EntityUtils.toString(entity));

		//		URL myurl = new URL(url);
		//		HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		//		InputStream ins = con.getInputStream();
		//		InputStreamReader isr = new InputStreamReader(ins);
		//		BufferedReader in = new BufferedReader(isr);
		//
		//		String inputLine;
		//
		//		while ((inputLine = in.readLine()) != null)
		//		{
		//			System.out.println(inputLine);
		//		}
		//
		//		in.close();
		//
		//
		//		DefaultAsyncHttpClient f;

	}
}
