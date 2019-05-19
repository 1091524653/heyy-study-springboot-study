package com.heyy.study.springbootstudycentral.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Classname TrustAllClientRequestFactory
 * @Description trust all certs
 * @Date 2019/5/11 10:01
 * @Created by Breeze
 */
@Slf4j
public class TrustAllClientRequestFactory extends SimpleClientHttpRequestFactory{
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if(!(connection instanceof HttpsURLConnection)){
            throw new RuntimeException("an instance of HttpsURLConnection required");
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)connection;
        TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
//                        return new X509Certificate[0];
                        return null;
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,trustManagers , new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier((hostname,session) -> true);
        } catch (Exception e) {
            log.error("prepareConnection occur exception",e);
        }

    }
}
