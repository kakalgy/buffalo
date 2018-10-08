package com.yusj.buffalo.jute;

/**
 * @Description Interface that acts as an iterator for deserializing maps.
 * * The deserializer returns an instance that the record uses to
 * * read vectors and maps. An example of usage is as follows:
 * *
 * * <code>
 * * Index idx = startVector(...);
 * * while (!idx.done()) {
 * *   .... // read element of a vector
 * *   idx.incr();
 * * }
 * * </code>
 * @Author kakalgy
 * @Date 2018/10/8 23:13
 **/
public interface Index {
    public boolean done();

    public void incr();
}
