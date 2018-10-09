package com.yusj.pangolin.jute.compiler;

/**
 * @Description
 * @Author kakalgy
 * @Date 2018/10/8 22:31
 **/
public class JByte extends JType {

    /**
     * Creates a new instance of JByte
     */
    public JByte() {
        super("char", "int8_t", "byte", "byte", "Byte", "Byte", "byte", "toByte");
    }

    public String getSignature() {
        return "b";
    }
}
