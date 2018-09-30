package com.yusj.buffalo.util;

/**
 * 生成zxid的工具类:
 * 为了保证事务的顺序一致性，zookeeper采用了递增的事务id号（zxid）来标识事务。
 * 所有的提议（proposal）都在被提出的时候加上 了zxid。实现中zxid是一个64位的数字，
 * 它高32位是epoch用来标识leader关系是否改变，每次一个leader被选出来，它都会有一个 新的epoch，
 * 标识当前属于那个leader的统治时期。低32位用于递增计数。
 * </p>
 * 全文地址请点击：https://blog.csdn.net/gs80140/article/details/51496925?utm_source=copy
 */
public class ZxidUtils {
    public static long getEpochFromZxid(long zxid) {
        return zxid >> 32L;
    }

    public static long getCounterFromZxid(long zxid) {
        return zxid & 0xffffffffL;
    }

    public static long makeZxid(long epoch, long counter) {
        return (epoch << 32L) | (counter & 0xffffffffL);
    }

    public static String zxidToString(long zxid) {
        return Long.toHexString(zxid);
    }

    public static void main(String[] args) {
        System.out.println(getEpochFromZxid(100));
        System.out.println(getCounterFromZxid(100));
        System.out.println(makeZxid(100, 10));
        System.out.println(zxidToString(100));
        System.out.println(Long.toBinaryString(100));
        System.out.println(Long.toBinaryString(getEpochFromZxid(100)));
        System.out.println(Long.toBinaryString(getCounterFromZxid(100)));
        System.out.println(Long.toBinaryString(makeZxid(100, 10)));
//        System.out.println(Long.toBinaryString(zxidToString(100)));
    }
}
