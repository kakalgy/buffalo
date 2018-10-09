package com.yusj.pangolin.jute.compiler;

/**
 * @Description
 * @Author kakalgy
 * @Date 2018/10/8 22:32
 **/
public class JFloat extends JType {

    /**
     * Creates a new instance of JFloat
     */
    public JFloat() {
        super("float", "float", "float", "float", "Float", "Float", "float", "toFloat");
    }

    public String getSignature() {
        return "f";
    }

    public String genJavaHashCode(String fname) {
        return "    ret = Float.floatToIntBits(" + fname + ");\n";
    }
}
