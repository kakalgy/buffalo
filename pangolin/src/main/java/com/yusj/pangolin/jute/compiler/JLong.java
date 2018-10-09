package com.yusj.pangolin.jute.compiler;

/**
 * @Description
 * @Author kakalgy
 * @Date 2018/10/8 22:30
 **/
public class JLong extends JType {

    /**
     * Creates a new instance of JLong
     */
    public JLong() {
        super("int64_t", "int64_t", "long", "long", "Long", "Long", "long", "toLong");
    }

    public String getSignature() {
        return "l";
    }

    public String genJavaHashCode(String fname) {
        return "    ret = (int) (" + fname + "^(" + fname + ">>>32));\n";
    }
}
