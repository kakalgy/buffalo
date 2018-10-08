package com.yusj.buffalo.jute.compiler;

/**
 * @Description
 * @Author kakalgy
 * @Date 2018/10/8 22:55
 **/
public class JDouble extends JType {

    /**
     * Creates a new instance of JDouble
     */
    public JDouble() {
        super("double", "double", "double", "double", "Double", "Double", "double", "toDouble");
    }

    public String getSignature() {
        return "d";
    }

    public String genJavaHashCode(String fname) {
        String tmp = "Double.doubleToLongBits(" + fname + ")";
        return "    ret = (int)(" + tmp + "^(" + tmp + ">>>32));\n";
    }
}
