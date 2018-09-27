package com.yusj.buffalo.common;

import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class NetUtilsTest {


    /**
     * InerAddress:
     * <p>
     * IP地址:在网络上唯一标示一台计算机
     * 端口号:标示计算机上不同的应用程序
     * java.net.InetAddress类:此类表示互联网协议 (IP) 地址。
     * 常用方法:
     * getByName(String host) 在给定主机名的情况下确定主机的 IP 地址。
     * getHostName() 获取此 IP地址的主机名。
     * getHostAddress()返回 IP 地址字符串（以文本表现形式）。
     * getLocalHost() 返回本地主机。
     * getAllByName(String host) 在给定主机名的情况下，根据系统上配置的名称服务返回其 IP 地址所组成的数组。
     */

    @Test
    public void testInerAddress() {
        try {
            //在给定主机名的情况下确定主机的 IP 地址。
//            InetAddress inetAddress = InetAddress.getByName("P-PC");
            InetAddress inetAddress = InetAddress.getLocalHost();//获取本地主机
            System.out.println(inetAddress);
            String hostName = inetAddress.getHostName();
            System.out.println("主机名:" + hostName);
            String ip = inetAddress.getHostAddress();
            System.out.println("IP地址:" + ip);
            //根据主机名或域名获取其IP地址
            InetAddress[] ids = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress inetAddress2 : ids) {
                System.out.println(inetAddress2);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * InetSocketAddress:
     * <p>
     * *java.net.InetSocketAddress类:此类实现 IP 套接字地址（IP 地址 + 端口号）。
     * *构造方法
     * *InetSocketAddress(InetAddress addr, int port)根据 IP 地址和端口号创建套接字地址。
     * *InetSocketAddress(String hostname, int port) 根据主机名和端口号创建套接字地址。
     * *常用的方法:
     * *	getAddress():获取 InetAddress。
     * *	getPort() 获取端口号。
     * *	toString() 构造此 InetSocketAddress 的字符串表示形式。(主机名/Ip:端口号)
     * *	getHostName()获取 主机名。
     */

    @Test
    public void testInetSocketAddress() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 3306);
            System.out.println(socketAddress);
            InetAddress inetAddress = socketAddress.getAddress();
            System.out.println("主机信息:" + inetAddress);
            int port = socketAddress.getPort();
            System.out.println("端口号:" + port);
            String hostName = socketAddress.getHostName();
            System.out.println("主机名:" + hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
