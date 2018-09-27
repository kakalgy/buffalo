package com.yusj.buffalo.common;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * This class contains common utilities for netstuff. Like printing IPv6 literals correctly</p>
 * 格式化IP和端口（包含IPV6）
 */
public class NetUtils {

    public static String formatInetAddr(InetSocketAddress addr) {
        InetAddress ia = addr.getAddress();

        if (ia == null) {
            return String.format("%s:%s", addr.getHostString(), addr.getPort());
        }

        if (ia instanceof Inet6Address) {
            return String.format("[%s]:%s", ia.getHostAddress(), addr.getPort());
        } else {
            return String.format("%s:%s", ia.getHostAddress(), addr.getPort());
        }
    }

    public static void main(String[] args) {
        InetSocketAddress socketAddress = null;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 3306);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(formatInetAddr(socketAddress));
    }
}
