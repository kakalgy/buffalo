package com.yusj.rhinoceros.common;

import java.util.Date;

/**
 * <ul>
 * <li>currentTimeMillis返回的是系统当前时间和1970-01-01之前间隔时间的毫秒数，如果系统时间固定则方法返回值也是一定的（这么说是为了强调和nanoTime的区别），精确度是毫秒级别的</li>
 * <li>nanoTime的返回值本身则没有什么意义，因为它基于的时间点是随机的，甚至可能是一个未来的时间，所以返回值可能为负数。但是其精确度为纳秒，相对高了不少。</li>
 * <li>currentTimeMillis不仅可以用来计算代码执行消耗的时间 ，也可以和Date类方便的转换。而nanoTime则不行</li>
 * <li>可以这么说吧，currentTimeMillis是一个时钟，而nanoTime是一个计时器，你可以用时钟来计算时间差，也可以用来单纯的看时间，但是作为计时器的nanoTime则只能用来计算时间差，好在优点是精确度高</li>
 * <li>currentTimeMillis是基于系统时间的，也就是说如果你再程序执行期间更改了系统时间则结果就会出错，而nanoTime是基于CPU的时间片来计算时间的，无法人为干扰</li>
 * <li>前面说了nanoTime基于的时间点是随机的，但是对于同一个JVM里，不同地方使用到的基点时间是一样的</li>
 * </ul>
 */
public class Time {
    /**
     * Returns time in milliseconds as does System.currentTimeMillis(),
     * but uses elapsed time from an arbitrary epoch more like System.nanoTime().
     * The difference is that if somebody changes the system clock,
     * Time.currentElapsedTime will change but nanoTime won't. On the other hand,
     * all of ZK assumes that time is measured in milliseconds.
     *
     * @return The time in milliseconds from some arbitrary point in time.
     */
    public static long currentElapsedTime() {

        return System.nanoTime() / 1000000;

    }

    /**
     * Explicitly(明确的) returns system dependent current wall time(实际时间).
     *
     * @return Current time in msec.
     */
    public static long currentWallTime() {

        return System.currentTimeMillis();
    }

    /**
     * This is to convert the elapsedTime to a Date.
     *
     * @return A date object indicated by the elapsedTime.
     */
    public static Date elapsedTimeToDate(long elapsedTime) {
        long wallTime = currentWallTime() + elapsedTime - currentElapsedTime();
        return new Date(wallTime);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(elapsedTimeToDate(System.nanoTime()/1000000));
    }
}
