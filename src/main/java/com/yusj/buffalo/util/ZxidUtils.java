package com.yusj.buffalo.util;

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
    }
}
