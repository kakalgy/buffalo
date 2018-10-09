package com.yusj.rhinoceros.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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

    public static final String JUTE_MAXBUFFER = "jute.maxbuffer";
    /**
     * Path to a kinit binary: {@value}. Defaults to
     * <code>"/usr/bin/kinit"</code>
     */
    public static final String KINIT_COMMAND = "zookeeper.kinit";

    public static final String JGSS_NATIVE = "sun.security.jgss.native";

    private final Map<String, String> properties = new HashMap<>();

    /**
     * Get the property value
     *
     * @param key
     * @return property value
     */
    public String getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Get the property value, if it is null return default value
     *
     * @param key          property key
     * @param defaultValue
     * @return property value or default value
     */
    public String getProperty(String key, String defaultValue) {
        String value = properties.get(key);
        return (value == null) ? defaultValue : value;
    }
}
