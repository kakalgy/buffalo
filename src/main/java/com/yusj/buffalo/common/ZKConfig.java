package com.yusj.buffalo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description This class is a base class for the configurations of both client and server.
 * * It supports reading client configuration from both system properties and
 * * configuration file. A user can override any system property by calling
 * * {@link #setProperty(String, String)}.
 * * @since 3.5.2
 * @Author kakalgy
 * @Date 2018/9/27 22:53
 **/
public class ZKConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZKConfig.class);

    @SuppressWarnings("deprecation")
    public static final String SSL_KEYSTORE_LOCATION = X509Util.SSL_KEYSTORE_LOCATION;
    @SuppressWarnings("deprecation")
    public static final String SSL_KEYSTORE_PASSWD = X509Util.SSL_KEYSTORE_PASSWD;
    @SuppressWarnings("deprecation")
    public static final String SSL_TRUSTSTORE_LOCATION = X509Util.SSL_TRUSTSTORE_LOCATION;
    @SuppressWarnings("deprecation")
    public static final String SSL_TRUSTSTORE_PASSWD = X509Util.SSL_TRUSTSTORE_PASSWD;
    @SuppressWarnings("deprecation")
    public static final String SSL_AUTHPROVIDER = X509Util.SSL_AUTHPROVIDER;
}
