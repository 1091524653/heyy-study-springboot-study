package com.heyy.study.springbootstudycentral.config;

import com.heyy.study.springbootstudycentral.constants.CommonConstants;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.integration.http.converter.SerializingHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RestemplateConfig
 * @Description create Restemplate instance
 * @Date 2019/5/11 09:26
 * @Created by Breeze
 */
@Configuration
public class RestemplateConfig {

    @Value("${http.proxy.host}")
    private String proxyHost;
    @Value("${http.proxy.port}")
    private int proxyPort;

    private static List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();

    static {
        messageConverterList.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverterList.add(new SerializingHttpMessageConverter());
        messageConverterList.add(new FormHttpMessageConverter());
        messageConverterList.add(new MappingJackson2HttpMessageConverter());
        messageConverterList.add(new MappingJackson2XmlHttpMessageConverter());
    }

    @Bean(name = "simpleRequestFactory")
    public SimpleClientHttpRequestFactory simpleRequestFactory(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(CommonConstants.RESTEMPLATE_CONNECT_TIMEOUT);
        simpleClientHttpRequestFactory.setReadTimeout(CommonConstants.RESTEMPLATE_READ_TIMEOUT);
        return simpleClientHttpRequestFactory;
    }

    @Bean(name = "proxyRequestFactory")
    public SimpleClientHttpRequestFactory proxyRequestFactory(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(CommonConstants.RESTEMPLATE_CONNECT_TIMEOUT);
        simpleClientHttpRequestFactory.setReadTimeout(CommonConstants.RESTEMPLATE_READ_TIMEOUT);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.proxyHost,this.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,inetSocketAddress);
        simpleClientHttpRequestFactory.setProxy(proxy);
        return simpleClientHttpRequestFactory;
    }

    @Bean(name = "igonreSSLandProxyReuqestFactory")
    public HttpComponentsClientHttpRequestFactory igonreSSLandProxyReuqestFactory() throws Exception{
        TrustStrategy trustStrategy = (final X509Certificate[] chain,final String authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null,trustStrategy).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(closeableHttpClient);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(CommonConstants.RESTEMPLATE_CONNECT_TIMEOUT);
        httpComponentsClientHttpRequestFactory.setReadTimeout(CommonConstants.RESTEMPLATE_READ_TIMEOUT);
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean(name = "ignoreSSLRequestFactory")
    public TrustAllClientRequestFactory ignoreSSLRequestFactory(){
        TrustAllClientRequestFactory trustAllClientRequestFactory = new TrustAllClientRequestFactory();
        trustAllClientRequestFactory.setConnectTimeout(CommonConstants.RESTEMPLATE_CONNECT_TIMEOUT);
        trustAllClientRequestFactory.setReadTimeout(CommonConstants.RESTEMPLATE_READ_TIMEOUT);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.proxyHost,this.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,inetSocketAddress);
        trustAllClientRequestFactory.setProxy(proxy);
        return trustAllClientRequestFactory;

    }

    @Bean(name = "ignoreSSLRestTemplate")
    public RestTemplate ignoreSSLRestTemplate(final TrustAllClientRequestFactory trustAllClientRequestFactory){
        RestTemplate restTemplate = new RestTemplate(messageConverterList);
        restTemplate.setRequestFactory(trustAllClientRequestFactory);
        return restTemplate;
    }

    @Bean(name = "simpleRestTemplate")
    public RestTemplate simpleRestTemplate(final SimpleClientHttpRequestFactory simpleRequestFactory){
        RestTemplate restTemplate = new RestTemplate(messageConverterList);
        restTemplate.setRequestFactory(simpleRequestFactory);
        return restTemplate;
    }



}
