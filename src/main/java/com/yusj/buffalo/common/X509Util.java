package com.yusj.buffalo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

/**
 * @Description Utility code for X509 handling
 * @Author kakalgy
 * @Date 2018/9/27 23:13
 **/
public class X509Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(X509Util.class);

    /**
     * @deprecated Use {@link ZKConfig#SSL_KEYSTORE_LOCATION} instead.
     */
    @Deprecated
    public static final String SSL_KEYSTORE_LOCATION = "zookeeper.ssl.keyStore.location";
    /**
     * @deprecated Use {@link ZKConfig#SSL_KEYSTORE_PASSWD} instead.
     */
    @Deprecated
    public static final String SSL_KEYSTORE_PASSWD = "zookeeper.ssl.keyStore.password";
    /**
     * @deprecated Use {@link ZKConfig#SSL_TRUSTSTORE_LOCATION} instead.
     */
    @Deprecated
    public static final String SSL_TRUSTSTORE_LOCATION = "zookeeper.ssl.trustStore.location";
    /**
     * @deprecated Use {@link ZKConfig#SSL_TRUSTSTORE_PASSWD} instead.
     */
    @Deprecated
    public static final String SSL_TRUSTSTORE_PASSWD = "zookeeper.ssl.trustStore.password";
    /**
     * @deprecated Use {@link ZKConfig#SSL_AUTHPROVIDER} instead.
     */
    @Deprecated
    public static final String SSL_AUTHPROVIDER = "zookeeper.ssl.authProvider";



    public static X509TrustManager createTrustManager(String trustStoreLocation, String trustStorePassword) throws X509Exception.TrustManagerException {
        FileInputStream inputStream = null;
        try {
            char[] trustStorePasswordChars = trustStorePassword.toCharArray();
            File trustStoreFile = new File(trustStoreLocation);
            KeyStore ts = KeyStore.getInstance("JKS");
            inputStream = new FileInputStream(trustStoreFile);
            ts.load(inputStream, trustStorePasswordChars);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);

            for (TrustManager tm : tmf.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    return (X509TrustManager) tm;
                }
            }
            throw new X509Exception.TrustManagerException("Couldn't find X509TrustManager");

        } catch (Exception e) {
            throw new X509Exception.TrustManagerException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("X509TrustManager.IOException " + e.getMessage(), e);
                }
            }
        }
    }
}
