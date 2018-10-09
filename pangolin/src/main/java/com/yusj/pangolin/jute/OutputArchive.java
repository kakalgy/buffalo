package com.yusj.pangolin.jute;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description Interface that all the serializers have to implement.
 * @Author kakalgy
 * @Date 2018/10/8 23:15
 **/
public interface OutputArchive {
    public void writeByte(byte b, String tag) throws IOException;

    public void writeBool(boolean b, String tag) throws IOException;

    public void writeInt(int i, String tag) throws IOException;

    public void writeLong(long l, String tag) throws IOException;

    public void writeFloat(float f, String tag) throws IOException;

    public void writeDouble(double d, String tag) throws IOException;

    public void writeString(String s, String tag) throws IOException;

    public void writeBuffer(byte buf[], String tag) throws IOException;

    public void writeRecord(Record r, String tag) throws IOException;

    public void startRecord(Record r, String tag) throws IOException;

    public void endRecord(Record r, String tag) throws IOException;

    public void startVector(List<?> v, String tag) throws IOException;

    public void endVector(List<?> v, String tag) throws IOException;

    public void startMap(TreeMap<?, ?> v, String tag) throws IOException;

    public void endMap(TreeMap<?, ?> v, String tag) throws IOException;
}
